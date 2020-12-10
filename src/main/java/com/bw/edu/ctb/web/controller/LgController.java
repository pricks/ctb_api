package com.bw.edu.ctb.web.controller;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.ThirdTypeEnum;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dao.entity.usr.Login;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import com.bw.edu.ctb.domain.UsrE;
import com.bw.edu.ctb.dto.UsrDTO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
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

    /** 登录保持 */
    @PostMapping("/sl")
    public Result stay_login(HttpServletRequest request, HttpSession session){
        try{
            String uid = request.getParameter("id");
            String token = request.getParameter("atk");
            if(StringUtil.isEmpty(uid) || StringUtil.isEmpty(token)){
                return Result.success(false);//false表示登录失败
            }
            Result<BUsr> bUsrRS = usrService.getById(Long.valueOf(uid)+SystemConstants.ID_DIFF);
            BUsr bUsr = bUsrRS.getData();
            if(null==bUsr){
                logger.error("[hacker attach!] not existed usr");
                return Result.success(false);//false表示登录失败
            }
            if(!token.equals(bUsr.getToken())){
                logger.error("[hacker attach!] token error");
                return Result.success(false);
            }
            if(System.currentTimeMillis() >= bUsr.getExpire()){
                logger.error("login expired");
                return Result.success(false);
            }
            Result<Void> lrs = usrService.login(new Login(bUsr.getId(), request.getRemoteAddr()));
            if(!lrs.isSuccess()){
                logger.error("write login record failed. remote ip="+request.getRemoteAddr());
                return Result.failure();
            }
            return Result.success();
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
    public Result thid_lg(UserVO user, HttpServletRequest request, HttpSession session){
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
                TUsr tUsr = rs.getData();
                if(null == tUsr){
                    Result<BUsr> createRS = usrService.createNewTUsr(build(user));
                    BUsr bUsr = createRS.getData();
                    UsrDTO usrDTO = new UsrDTO();
                    usrDTO.setId(bUsr.getId()- SystemConstants.ID_DIFF);
                    usrDTO.setAtk(bUsr.getToken());

                    Result<Void> lrs = usrService.login(new Login(bUsr.getId(), request.getRemoteAddr()));
                    if(!lrs.isSuccess()){
                        logger.error("write login record failed. new nick="+user.getNick()+"remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }
                    return Result.success(usrDTO);
                }else{
                    UsrDTO usrDTO = new UsrDTO();
                    usrDTO.setId(tUsr.getUid()- SystemConstants.ID_DIFF);
                    usrDTO.setAtk(tUsr.getAtk());

                    //延长token
                    Result<Void> urs = usrService.updateToken(tUsr.getUid());
                    if(!urs.isSuccess()){
                        logger.error("token update failed. nick="+user.getNick()+"remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }

                    //写登录记录
                    Result<Void> lrs = usrService.login(new Login(tUsr.getUid(), request.getRemoteAddr()));
                    if(!lrs.isSuccess()){
                        logger.error("write login record failed. old nick="+user.getNick()+", remote ip="+request.getRemoteAddr());
                        return Result.failure();
                    }
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
