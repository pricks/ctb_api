package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.mapper.UnitMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitManager {
    @Autowired
    private UnitMapper unitMapper;

    public List<UnitEntity> queryByCl(UnitQO uq){
        if(null==uq.getCl() || uq.getCl()<=0){
            throw new CtbException(CtbExceptionEnum.PARAM_NULL, uq.toString());
        }
        List<UnitEntity> unitEntityList = unitMapper.selectByPage(uq);
        if(unitEntityList.size() > 30){
            throw new CtbException(CtbExceptionEnum.UNIT_TOO_MANY, uq.toString());
        }
        return unitEntityList;
    }
}
