package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.ExRecEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExRecMapper {
    int save(ExRecEntity exRecEntity);
    Long selectLatestExr(Long uid);
}