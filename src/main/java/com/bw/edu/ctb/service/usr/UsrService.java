package com.bw.edu.ctb.service.usr;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.AuthTypeEnum;
import com.bw.edu.ctb.common.enums.ThirdTypeEnum;
import com.bw.edu.ctb.dao.entity.usr.AURel;
import com.bw.edu.ctb.dao.entity.usr.BUsr;
import com.bw.edu.ctb.dao.entity.usr.Login;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import com.bw.edu.ctb.domain.UsrE;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.usr.UsrManager;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.bw.edu.ctb.common.util.DateUtil;
import com.bw.edu.ctb.common.util.TokenGenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsrService {
    private final static Logger logger = LoggerFactory.getLogger(UsrService.class);
    @Autowired
    private UsrManager usrManager;

    public Result<BUsr> getById(Long uid){
        try {
            if(null==uid || uid<=0){
                return Result.success();
            }
            BUsr bUsr = usrManager.getById(uid);
            return Result.success(bUsr);
        } catch (CtbException e){
            logger.error("getById failed. uid="+uid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("getById failed because system. uid="+uid, e);
            return Result.failure();
        }
    }

    /**
     * todo add cache logic
     * @param token
     * @return
     */
    public Result<BUsr> getByAtk(String token){
        try {
            if(StringUtil.isEmpty(token)){
                return Result.success();
            }
            BUsr bUsr = usrManager.getByAtk(token);
            return Result.success(bUsr);
        } catch (CtbException e){
            logger.error("getById failed. atk="+token);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("getById failed because system. atk="+token, e);
            return Result.failure();
        }
    }

    public Result<BUsr> updateToken(Long uid){
        try {
            if(null==uid){
                return Result.failure();
            }
            BUsr bUsr = new BUsr();
            bUsr.setId(uid);
            bUsr.setExpire(DateUtil.addDay(new Date(), SystemConstants.EXPIRE_DAYS).getTime());
            bUsr.setToken(TokenGenUtil.genToken(uid));
            usrManager.updateToken(bUsr);
            return Result.success(bUsr);
        } catch (CtbException e){
            logger.error("getById failed. uid="+uid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("getById failed because system. uid="+uid, e);
            return Result.failure();
        }
    }

    /** 根据nick查询第三方登录用户 */
    public Result<TUsr> queryTusrByNick(String nick, Integer type){
        if(StringUtil.isEmpty(nick) || null==type || StringUtil.isEmpty(ThirdTypeEnum.getName(type))){
            return Result.failure(CtbExceptionEnum.USER_INFO_NULL.getCode(), CtbExceptionEnum.USER_INFO_NULL.getDesc());
        }
        List<TUsr> usrList = usrManager.queryTusrByNick(nick, type);
        if(CollectionUtil.isEmpty(usrList)) return Result.success();
        if(usrList.size() > 1){
            logger.error("[fatal error] many same tusrs. nick="+nick+", type="+type);
            return Result.failure(CtbExceptionEnum.MULTIPLE_SAME_TUSR.getCode(), CtbExceptionEnum.MULTIPLE_SAME_TUSR.getDesc());
        }
        return Result.success(usrList.get(0));
    }

    /**
     * 创建第三方新用户 todo 这里要加事务
     * @return
     */
    public Result<BUsr> createNewTUsr(UsrE usrE){
        BUsr bUsr = new BUsr();
        try{
            bUsr.setExpire(DateUtil.addDay(new Date(), SystemConstants.EXPIRE_DAYS).getTime());
            bUsr.setToken(TokenGenUtil.genToken());
            bUsr.setAvatar(usrE.getAvatar());
            usrManager.createBusr(bUsr);

            TUsr tUsr = new TUsr();
            tUsr.setNick(usrE.getNick());
            tUsr.setUid(bUsr.getId());
            tUsr.setType(usrE.getType());
            tUsr.setOid(usrE.getOid());
            tUsr.setAtk(bUsr.getToken());
            tUsr.setAts(usrE.getAts());
            usrManager.createTusr(tUsr);

            AURel auRel = new AURel();
            auRel.setUid(bUsr.getId());
            auRel.setAid(tUsr.getId());
            auRel.setType(AuthTypeEnum.THIRD.getCode());
            usrManager.createAurel(auRel);
        } catch (CtbException e){
            logger.error("create new third user failed. usr="+usrE);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("create new third user failed because system. usr="+usrE, e);
            return Result.failure();
        }

        return Result.success(bUsr);
    }

    public Result<Void> login(Login login){
        try {
            if(null==login || login.getUid()<=0 || StringUtil.isEmpty(login.getIp())){
                return Result.success();
            }
            usrManager.createLogin(login);
            return Result.success();
        } catch (CtbException e){
            logger.error("login failed. login="+login);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("login failed because system. login="+login, e);
            return Result.failure();
        }
    }
}
