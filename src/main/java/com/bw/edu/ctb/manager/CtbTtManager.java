package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.CtbTtQO;
import com.bw.edu.ctb.dao.entity.CtbTtEntity;
import com.bw.edu.ctb.dao.mapper.CtbTtMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtbTtManager {
    @Autowired
    private CtbTtMapper ctbTtMapper;

    public void save(CtbTtEntity ct){
        int rs = ctbTtMapper.save(ct);
        if(rs < 1){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, ct.toString());
        }
    }

    public List<CtbTtEntity> query(CtbTtQO qo){
        return ctbTtMapper.query(qo);
    }

    public Integer countToday(CtbTtQO qo){
        return ctbTtMapper.countToday(qo);
    }
}
