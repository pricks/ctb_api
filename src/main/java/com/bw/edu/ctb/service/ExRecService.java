package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.KptBatchStatusEnum;
import com.bw.edu.ctb.common.qo.ExRecQO;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.ExRecManager;
import com.bw.edu.ctb.manager.KptBatchManager;
import com.bw.edu.ctb.notify.PAC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExRecService {
    private final static Logger logger = LoggerFactory.getLogger(ExRecService.class);
    @Autowired
    private ExRecManager exRecManager;
    @Autowired
    private KptBatchManager kptBatchManager;
    @Autowired
    private PAC pac;

    /**
     * @param exRecEntity
     * @return
     */
    public Result<Long> create(ExRecEntity exRecEntity){
        try {
            if(null==exRecEntity || null==exRecEntity.getBid()){
                return Result.success();
            }

            writeDB(exRecEntity);

            pac.produce(exRecEntity.getId());
            return Result.success(exRecEntity.getId());
        } catch (CtbException e){
            logger.error("createExrec failed. exRecEntity="+exRecEntity);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("createExrec failed because system. exRecEntity="+exRecEntity, e);
            return Result.failure();
        }
    }

    @Transactional(transactionManager = "basicTransactionManager", rollbackFor = Throwable.class)
    private void writeDB(ExRecEntity exRecEntity) {
        kptBatchManager.updateStatus(exRecEntity.getBid(), KptBatchStatusEnum.CREATED.getCode(), KptBatchStatusEnum.COMMITED.getCode());
        exRecManager.save(exRecEntity);
    }

    public Result<ExRecEntity> queryByBid(Long bid){
        try {
            if(null==bid || bid<1L){
                return Result.success();
            }
            return Result.success(exRecManager.queryByBid(bid));
        } catch (CtbException e){
            logger.error("failed. bid="+bid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("failed because system. bid="+bid, e);
            return Result.failure();
        }
    }

    public Result<Long> selectLatestExr(Long uid){
        try {
            if(null==uid || uid<1L){
                return Result.success();
            }
            return Result.success(exRecManager.selectLatestExr(uid));
        } catch (CtbException e){
            logger.error("selectLatestExr failed. uid="+uid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("selectLatestExr failed because system. uid="+uid, e);
            return Result.failure();
        }
    }

    public Result<Long> selectLatestExrByCl(ExRecQO exRecQO){
        try {
            if(null==exRecQO || null==exRecQO.getUid() || exRecQO.getUid()<1L || null==exRecQO.getCl()){
                logger.error("exRecQO is null");
                return Result.success();
            }
            return Result.success(exRecManager.selectLatestExrByCl(exRecQO));
        } catch (CtbException e){
            logger.error("biz error. exRecQO="+exRecQO);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("system error. exRecQO="+exRecQO, e);
            return Result.failure();
        }
    }
}
