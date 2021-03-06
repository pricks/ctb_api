package com.bw.edu.ctb.service.usr;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.AuthTypeEnum;
import com.bw.edu.ctb.common.enums.ThirdTypeEnum;
import com.bw.edu.ctb.common.util.*;
import com.bw.edu.ctb.dao.entity.usr.*;
import com.bw.edu.ctb.domain.LUsrE;
import com.bw.edu.ctb.domain.UsrE;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.usr.UsrManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /** 本地登录相关 */
    public Result<LUsr> queryLusrByMb(String phone){
        if(StringUtil.isEmpty(phone)){
            return Result.failure(CtbExceptionEnum.USER_INFO_NULL.getCode(), CtbExceptionEnum.USER_INFO_NULL.getDesc());
        }
        try{
            return Result.success(usrManager.getByMb(phone));
        } catch (CtbException e){
            logger.error("biz-err. mb="+phone);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. mb="+phone, e);
            return Result.failure();
        }
    }
    @Transactional(transactionManager = "basicTransactionManager", rollbackFor = Exception.class)
    public Result<BUsr> createNewLUsr(LUsrE usrE){//创建本地用户
        BUsr bUsr = new BUsr();
        try{
            bUsr.setExpire(DateUtil.addDay(new Date(), SystemConstants.EXPIRE_DAYS).getTime());
            bUsr.setToken(TokenGenUtil.genToken());
            usrManager.createBusr(bUsr);

            LUsr lUsr = new LUsr();
            lUsr.setUid(bUsr.getId());
            lUsr.setMb(usrE.getPhone());
            lUsr.setPwd(MD5Util.stringToMD5(usrE.getPwd()));
            lUsr.setAtk(bUsr.getToken());
            lUsr.setAts(usrE.getAts());
            usrManager.createLusr(lUsr);

            AURel auRel = new AURel();
            auRel.setUid(bUsr.getId());
            auRel.setAid(lUsr.getId());
            auRel.setType(AuthTypeEnum.LOCAL.getCode());
            usrManager.createAurel(auRel);
        } catch (CtbException e){
            logger.error("biz-err. usr="+usrE);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. usr="+usrE, e);
//            return Result.failure();
            throw new RuntimeException();//这里要抛出异常，否则事务没法回滚
        }

        return Result.success(bUsr);
    }

    /** 根据nick查询第三方登录用户 */
    public Result<TUsr> queryTusrByOid(String openId, Integer type){
        if(StringUtil.isEmpty(openId) || null==type || StringUtil.isEmpty(ThirdTypeEnum.getName(type))){
            return Result.failure(CtbExceptionEnum.USER_INFO_NULL.getCode(), CtbExceptionEnum.USER_INFO_NULL.getDesc());
        }
        List<TUsr> usrList = usrManager.queryTusrByOpenId(openId, type);
        if(CollectionUtil.isEmpty(usrList)) return Result.success();
        if(usrList.size() > 1){
            logger.error("[fatal error] many same tusrs. openId="+openId+", type="+type);
            return Result.failure(CtbExceptionEnum.MULTIPLE_SAME_TUSR.getCode(), CtbExceptionEnum.MULTIPLE_SAME_TUSR.getDesc());
        }
        return Result.success(usrList.get(0));
    }
    @Transactional(transactionManager = "basicTransactionManager", rollbackFor = Throwable.class)
    public Result<BUsr> createNewTUsr(UsrE usrE){//创建三方用户：wx,zhifubao,weibo,toutiao...
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
//            return Result.failure();
            throw new RuntimeException();
        }

        return Result.success(bUsr);
    }

    public Result<Void> login(Login login){
        try {
            if(null==login || login.getUid()<0 || StringUtil.isEmpty(login.getIp())){
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
