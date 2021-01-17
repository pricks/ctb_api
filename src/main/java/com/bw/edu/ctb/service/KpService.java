package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.KpManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@Service
public class KpService {
    private final static Logger logger = LoggerFactory.getLogger(KpService.class);
    @Autowired
    private KpManager kpManager;

    public Result<List<KpEntity>> query(KpQO kpQO){
        try{
            if(null==kpQO || ((null==kpQO.getUn() || null==kpQO.getDl()) && null==kpQO.getId())){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED);
            }
            return Result.success(kpManager.query(kpQO));
        }catch (CtbException e){
            logger.error("biz-err. kq="+kpQO);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("sys-err. kq="+kpQO, e);
            return Result.failure();
        }
    }
}
