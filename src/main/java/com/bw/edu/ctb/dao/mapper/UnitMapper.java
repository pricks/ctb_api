package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    int save(UnitEntity unitEntity);
    List<UnitEntity> selectByPage(UnitQO unitQO);
    UnitEntity getByCode(Long code);
}
