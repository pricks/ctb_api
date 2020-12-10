package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.usr.BUsr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UbsrMapper {
    int save(BUsr bUsr);
    BUsr getByUid(Long uid);
    int updateToken(Long uid, String token, Long expire);
}
