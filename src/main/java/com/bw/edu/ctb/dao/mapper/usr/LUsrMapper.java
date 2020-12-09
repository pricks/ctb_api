package com.bw.edu.ctb.dao.mapper.usr;

import com.bw.edu.ctb.dao.entity.usr.LUsr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LUsrMapper {
    int save(LUsr lUsr);
    LUsr getByUid(Long uid);
}
