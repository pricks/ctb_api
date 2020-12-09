package com.bw.edu.ctb.dao.mapper.usr;

import com.bw.edu.ctb.dao.entity.usr.AURel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AURelMapper {
    int save(AURel auRel);
}
