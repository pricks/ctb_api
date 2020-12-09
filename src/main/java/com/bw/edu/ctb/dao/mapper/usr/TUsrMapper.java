package com.bw.edu.ctb.dao.mapper.usr;

import com.bw.edu.ctb.common.qo.usr.TUsrQO;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUsrMapper {
    int save(TUsr tUsr);
    List<TUsr> query(TUsrQO tUsrQO);
}
