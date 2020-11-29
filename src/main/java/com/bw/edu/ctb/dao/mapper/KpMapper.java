package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KpMapper {
    int save(KpEntity kpEntity);
    List<KpEntity> select(KpQO kpQO);
    KpEntity getById(Long id);
}
