package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.ThirdTypeEnum;
import com.bw.edu.ctb.common.enums.subjects.DGRel;
import com.bw.edu.ctb.common.enums.subjects.DagangEnum;
import com.bw.edu.ctb.common.enums.subjects.GradeEnum;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dao.entity.usr.Login;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import com.bw.edu.ctb.domain.UsrE;
import com.bw.edu.ctb.dto.GCDTO;
import com.bw.edu.ctb.dto.UsrDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.service.ExRecService;
import com.bw.edu.ctb.service.UnitService;
import com.bw.edu.ctb.service.usr.UsrService;
import com.bw.edu.ctb.util.StringUtil;
import com.bw.edu.ctb.web.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@RestController
@RequestMapping("/l")
public class LgController {
    private final static Logger logger = LoggerFactory.getLogger(LgController.class);
    @Autowired
    private UsrService usrService;
    @Autowired
    private ExRecService exRecService;
    @Autowired
    private UnitService unitService;

    /** get the grade & class */
    @PostMapping("/fgc")
    public Result<GCDTO> fetch_gc(HttpServletRequest request, HttpSession session){
        try{
            String token = request.getParameter("atk");
            Result<BUsr> bUsrRS = usrService.getByAtk(token);
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }

            Result<Long> unRS = exRecService.selectLatestExr(bUsr.getId());
            if(!unRS.isSuccess() || null==unRS.getData()){
                //获取默认的gd & class
                GCDTO gcdto = new GCDTO();
                gcdto.setDg(DagangEnum.RENJIAOBAN.getCode());
                gcdto.setGd(DGRel.D1_X1.getGe().getCode());
                gcdto.setGdn(DGRel.D1_X1.getGe().getName());
                return Result.success(gcdto);
            }

            Long un = unRS.getData();
            Result<UnitEntity> unitRs = unitService.getByCode(un);
            if(!unitRs.isSuccess()){
                return Result.failure(unRS.getCode(), unRS.getMessage());
            }
            UnitEntity ue = unitRs.getData();
            if(null==ue){
                logger.error("query no un. un="+un);
                return Result.failure();
            }
            GCDTO gcdto = new GCDTO();
            gcdto.setDg(ue.getDg());
            gcdto.setGd(ue.getGd());
            gcdto.setGdn(GradeEnum.getName(ue.getGd()));
            return Result.success(gcdto);
        }catch (CtbException e){
            logger.error("sl biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("sl sys-error", e);
            return Result.failure();
        }
    }

    /** 登录保持 */
    @PostMapping("/sl")
    public Result<UsrDTO> stay_login(HttpServletRequest request, HttpSession session){
        try{
            String token = request.getParameter("atk");
            Result<BUsr> bUsrRS = usrService.getByAtk(token);
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.failure();//表示登录失败
            }
            if(System.currentTimeMillis() >= bUsr.getExpire()){
                logger.error("login expired");
                return Result.failure();
            }
            Result<Void> lrs = usrService.login(new Login(bUsr.getId(), request.getRemoteAddr()));
            if(!lrs.isSuccess()){
                logger.error("write login record failed. remote ip="+request.getRemoteAddr());
                return Result.failure();
            }

            /**
             * 延长token
             *
             * 注意：这里可能会有数据不一致的情况：
             * 当DB里的token已经被更新后，但是服务
             * 器突然宕机了，导致没有来得及发送给客户端
             * ，于是app内还是老的token。下次app来
             * 请求时就会token校验不通过。
             * 不过这种情况并不是很常见，因此即使出现了，
             * 就让用户重新登录一次即可
             */
            Result<BUsr> urs = usrService.updateToken(bUsr.getId());
            if(!urs.isSuccess()){
                logger.error("token update failed. atk="+token+", remote ip="+request.getRemoteAddr());
                return Result.failure();
            }
            UsrDTO usrDTO = new UsrDTO();
            usrDTO.setAtk(urs.getData().getToken());
            return Result.success(usrDTO);
        }catch (CtbException e){
            logger.error("sl biz-error", e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("sl sys-error", e);
            return Result.failure();
        }
    }

    /** third login, such as weixin/alipay/toutiao... */
    @PostMapping("/tg")
    public Result<UsrDTO> thid_lg(UserVO user, HttpServletRequest request, HttpSession session){
        try{
            logger.error("g. r=" + request.getRemoteAddr() + ", model="+user);
            if(null==user || StringUtil.isEmpty(user.getNick())){
                promoteException(CtbExceptionEnum.USER_INFO_NULL);
            }
            if(null==user.getAtype() && null==user.getType()){
                promoteException(CtbExceptionEnum.USER_TYPE_NULL);
            }
            if(ThirdTypeEnum.WEIXIN.getCode().equals(user.getType())){
                Result<TUsr> rs = usrService.queryTusrByNick(user.getNick(), user.getType());
                if(!rs.isSuccess()){
                    promoteException(rs.getCode(),rs.getMessage());
                }
                TUsr tUsr = rs.getData();
                if(null == tUsr){
                    Result<BUsr> createRS = usrService.createNewTUsr(build(user));
                    BUsr bUsr = createRS.getData();
                    UsrDTO usrDTO = new UsrDTO();
                    usrDTO.setAtk(bUsr.getToken());

                    Result<Void> lrs = usrService.login(new Login(bUsr.getId(), request.getRemoteAddr()));
                    if(!lrs.isSuccess()){
                        logger.error("write login record failed. new nick="+user.getNick()+", remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }
                    return Result.success(usrDTO);
                }else{
                    //写登录记录
                    Result<Void> lrs = usrService.login(new Login(tUsr.getUid(), request.getRemoteAddr()));
                    if(!lrs.isSuccess()){
                        logger.error("write login record failed. old nick="+user.getNick()+", remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }

                    //延长token
                    Result<BUsr> urs = usrService.updateToken(tUsr.getUid());
                    if(!urs.isSuccess()){
                        logger.error("token update failed. nick="+user.getNick()+", remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }
                    UsrDTO usrDTO = new UsrDTO();
                    usrDTO.setAtk(urs.getData().getToken());
                    return Result.success(usrDTO);
                }
            }

            logger.error("[fatal error] unsupported login type. user="+user);
            promoteException(CtbExceptionEnum.UN_SUPPORTED_LOGIN_TYPE);
        }catch (CtbException e){
            logger.error("tg biz-error. usr="+user, e);
            return Result.failure(e);
        }catch (Exception e){
            logger.error("tg sys-error. usr="+user, e);
            return Result.failure();
        }
        return Result.failure();
    }

    private UsrE build(UserVO userVO){
        UsrE ue = new UsrE();
        ue.setNick(userVO.getNick());
        ue.setType(userVO.getType());
        ue.setAvatar(userVO.getAvatar());
        ue.setOid(userVO.getOpenId());

        //第三方账号(例如weixin)的token写到attributes里
        ue.setAts("token="+userVO.getAtk()+"unionId="+userVO.getUnionId()+",props="+userVO.getProps()+",gender="+userVO.getGender());
        return ue;
    }
}
