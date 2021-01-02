package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.manager.KptBatchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KptBatchService {
    private final static Logger logger = LoggerFactory.getLogger(KptBatchService.class);
    @Autowired
    private KptBatchManager kptBatchManager;

    public Result<KptBatchEntity> selectById(Long kid){
        try {
            if(null==kid || kid<1L){
                return Result.success();
            }
            return Result.success(kptBatchManager.selectById(kid));
        } catch (CtbException e){
            logger.error("selectById failed. kid="+kid);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("selectById failed because system. kid="+kid, e);
            return Result.failure();
        }
    }
}
