package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CtbTitleMapper {
    List<CtbTitleEntity> getAll();
    List<CtbTitleEntity> getById(Long id);
}
