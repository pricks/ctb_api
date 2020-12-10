package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.usr.AURel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UarelMapper {
    int save(AURel auRel);
}
