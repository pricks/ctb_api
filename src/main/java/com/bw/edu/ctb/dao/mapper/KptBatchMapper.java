package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.KptBatchQO;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KptBatchMapper {
    int save(KptBatchEntity kptBatchEntity);
    List<KptBatchEntity> select(KptBatchQO kptBatchQO);
    void updateStatus(Long id, Integer oriStatus, Integer tarStatus);
}
