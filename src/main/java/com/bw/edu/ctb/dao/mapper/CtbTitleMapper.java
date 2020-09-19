package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CtbTitleMapper {
    /**
     * 保存成功返回1，否则返回0
     * @param titleEntity
     * @return
     */
    int save(CtbTitleEntity titleEntity);
    List<CtbTitleEntity> selectByPage(TitleQO titleQO);
    List<CtbTitleEntity> getById(Long id);
}
