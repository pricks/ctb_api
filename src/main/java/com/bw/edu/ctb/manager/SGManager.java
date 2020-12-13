package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.dao.mapper.SGMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SGManager {
    @Autowired
    private SGMapper sgMapper;

    public void save(SGEntity sg){
        int rs = sgMapper.save(sg);
        if(rs < 1){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, sg.toString());
        }
    }

    public List<SGEntity> select(int dg, int gd){
        return sgMapper.select(dg, gd);
    }
}
