package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.CtbTtQO;
import com.bw.edu.ctb.dao.entity.CtbTtEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CtbTtMapper {
    int save(CtbTtEntity tt);
    List<CtbTtEntity> query(CtbTtQO qo);
    int countToday(CtbTtQO qo);
}
