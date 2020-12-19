package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.KptBatchStatusEnum;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.ExRecManager;
import com.bw.edu.ctb.manager.KptBatchManager;
import com.bw.edu.ctb.notify.PAC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * todo 添加事务
     * @param exRecEntity
     * @return
     */
    public Result<Long> create(ExRecEntity exRecEntity){
        try {
            if(null==exRecEntity || null==exRecEntity.getBatchId()){
                return Result.success();
            }

            kptBatchManager.updateStatus(exRecEntity.getBatchId(), KptBatchStatusEnum.CREATED.getCode(), KptBatchStatusEnum.COMMITED.getCode());
            exRecManager.save(exRecEntity);

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
}
