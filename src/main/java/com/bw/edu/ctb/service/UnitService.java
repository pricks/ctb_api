package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.ExSttQO;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.ExSttEntity;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.mapper.ExSttMapper;
import com.bw.edu.ctb.dao.mapper.UnitMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.UnitManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@Service
public class UnitService {
    private final static Logger logger = LoggerFactory.getLogger(UnitService.class);
    @Autowired
    private UnitManager unitManager;

    public Result<UnitEntity> getByCode(Long un){
        try {
            if(null==un || un<1L){
                promoteException(CtbExceptionEnum.PARAM_NOT_RULED);
            }
            return Result.success(unitManager.getByCode(un));
        } catch (CtbException e){
            logger.error("getByCode failed. un="+un);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("getByCode failed because system. un="+un, e);
            return Result.failure();
        }
    }

    public Result<List<UnitEntity>> queryByCl(UnitQO uq){
        return Result.success(unitManager.queryByCl(uq));
    }
}
