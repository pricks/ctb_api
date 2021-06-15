package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.AuthTypeEnum;
import com.bw.edu.ctb.common.enums.SortEnum;
import com.bw.edu.ctb.common.qo.CtbTtQO;
import com.bw.edu.ctb.common.util.*;
import com.bw.edu.ctb.dao.entity.CtbTtEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.usr.AURel;
import com.bw.edu.ctb.dao.entity.usr.LUsr;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.CtbTtManager;
import com.bw.edu.ctb.manager.TTManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@Service
public class CtbTtService {
    private final static Logger logger = LoggerFactory.getLogger(CtbTtService.class);
    @Autowired
    private CtbTtManager ctbTtManager;
    @Autowired
    private TTManager ttManager;

    @Transactional(transactionManager = "basicTransactionManager", rollbackFor = Throwable.class)
    public Result<Void> saveCtb(TTEntity tt, CtbTtEntity ct){
        try{
            ttManager.create(tt);

            ct.setTid(tt.getId());
            ctbTtManager.save(ct);
        } catch(Exception e){
            logger.error("sys-err. tt="+tt+", ct="+ct, e);
            throw new RuntimeException();//这里要抛出异常，否则事务没法回滚
        }
        return Result.success();
    }

    /**
     * 查询错题本对应的具体的题目
     * @param qo
     * @return
     */
    public Result<List<TTEntity>> queryTts(CtbTtQO qo){
        try{
            if(null==qo || null==qo.getUid() ){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED);
            }

            qo.setNum(5);//todo 测试，后面删掉

            //查询ctb tt
            qo.setSortProperty("gm");
            qo.setSortMode(SortEnum.ASC.getMode());
            List<CtbTtEntity> ctbTtEntities = ctbTtManager.query(qo);
            if(CollectionUtil.isEmpty(ctbTtEntities)){
                return Result.success();
            }

            //获取最后一条ctb tt的gm
            int size = ctbTtEntities.size();
            qo.setMaxGm(DateUtil.format(ctbTtEntities.get(size-1).getGm()));

            //查询tt
            List<Long> ttIds = new ArrayList<>(size);
            for(CtbTtEntity tt : ctbTtEntities){
                ttIds.add(tt.getTid());
            }
            return Result.success(ttManager.queryByIds(ttIds));
        }catch (CtbException e){
            logger.error("biz-err. qo="+qo);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. qo="+qo, e);
            return Result.failure();
        }
    }

    public Result<List<CtbTtEntity>> query(CtbTtQO qo){
        try{
            if(null==qo || null==qo.getUid() ){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED);
            }
            return Result.success(ctbTtManager.query(qo));
        }catch (CtbException e){
            logger.error("biz-err. qo="+qo);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. qo="+qo, e);
            return Result.failure();
        }
    }

    public Result<Integer> countToday(Long uid){
        try{
            if(null==uid){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED);
            }

            String todayZero = DateUtil.format(DateUtil.todayZero());
            CtbTtQO qo = new CtbTtQO();
            qo.setUid(uid);
            qo.setGc(todayZero);
            return Result.success(ctbTtManager.countToday(qo));
        }catch (CtbException e){
            logger.error("biz-err. uid="+uid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. uid="+uid, e);
            return Result.failure();
        }
    }
}
