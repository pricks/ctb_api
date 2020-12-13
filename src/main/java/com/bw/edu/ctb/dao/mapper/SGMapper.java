package com.bw.edu.ctb.dao.mapper;

import com.bw.edu.ctb.dao.entity.SGEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SGMapper {
    int save(SGEntity sgEntity);
    List<SGEntity> select(Integer dg, Integer gd);
}
