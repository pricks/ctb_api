package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.PPracBatchQO;
import com.bw.edu.ctb.dao.entity.PPracBatchEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PPracBatchMapper {

    /**
     * 查询指定条件下的最后批次
     * @return
     */
    public PPracBatchEntity selectLatest(PPracBatchQO pPracBatchQO);
}
