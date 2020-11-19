package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.ExSttQO;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.entity.ExSttEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExSttMapper {
    Integer save(ExSttEntity exSttEntity);
    List<ExSttEntity> selectByPage(ExSttQO exSttQO);
    List<ExSttEntity> selectProfile(ExSttQO exSttQO);
}
