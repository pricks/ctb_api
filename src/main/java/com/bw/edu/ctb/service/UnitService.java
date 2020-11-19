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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ExSttMapper exSttMapper;

    public Result<UnitEntity> queryExStt(UnitQO uq){
        //1.查询所有unit
        List<UnitEntity> unitEntityList = unitMapper.selectByPage(uq);
        if(unitEntityList==null || unitEntityList.size()==0){
            throw new CtbException(CtbExceptionEnum.UNIT_IS_NULL);
        }
        if(unitEntityList.size() > 30){
            throw new CtbException(CtbExceptionEnum.UNIT_TOO_MANY);
        }
        return Result.success(unitEntityList);
    }
}
