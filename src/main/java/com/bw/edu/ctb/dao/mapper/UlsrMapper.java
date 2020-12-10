package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.usr.LUsr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UlsrMapper {
    int save(LUsr lUsr);
    LUsr getByUid(Long uid);
}
