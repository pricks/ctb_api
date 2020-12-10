package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.usr.TUsrQO;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UtsrMapper {
    int save(TUsr tUsr);
    List<TUsr> select(TUsrQO tUsrQO);
}
