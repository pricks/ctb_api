package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.ExRecQO;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExRecMapper {
    int save(ExRecEntity exRecEntity);
    ExRecEntity getById(Long id);
    Long selectLatestExr(Long uid);
    Long selectLatestExrByCl(ExRecQO exRecQO);
    ExRecEntity queryByBid(Long bid);
}
