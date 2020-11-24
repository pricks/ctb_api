package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.TTQO;
import com.bw.edu.ctb.dao.entity.TTEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TTMapper {
    int save(TTEntity ttEntity);
    List<TTEntity> select(TTQO ttqo);
}
