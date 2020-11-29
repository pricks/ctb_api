package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.TkrQO;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TkrMapper {
    int save(TkrEntity tkrEntity);
    List<TkrEntity> select(TkrQO tkrQO);
}
