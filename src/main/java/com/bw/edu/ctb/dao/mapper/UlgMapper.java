package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.usr.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UlgMapper {
    int save(Login login);
}
