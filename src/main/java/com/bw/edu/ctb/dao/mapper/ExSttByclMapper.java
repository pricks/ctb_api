package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.dao.entity.ExSttByclEntity;

import java.util.List;

public interface ExSttByclMapper {
    int save(ExSttByclEntity exSttByclEntity);
    int update(ExSttByclEntity exSttByclEntity);
    List<ExSttByclEntity> select(ExSttByclQO exSttByclQO);

    /** 查询最近有效的记录，返回要么为空，要么返回一条数据 */
    ExSttByclEntity selectLatestValidated(ExSttByclQO exSttByclQO);
}
