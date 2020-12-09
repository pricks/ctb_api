package com.bw.edu.ctb.dao.mapper.usr;

import com.bw.edu.ctb.dao.entity.usr.BUsr;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BUsrMapper {
    int save(BUsr bUsr);
    BUsr getByUid(Long uid);
}
