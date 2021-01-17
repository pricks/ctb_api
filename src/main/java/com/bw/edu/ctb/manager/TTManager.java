package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.TTQO;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.TTMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTManager {
    @Autowired
    private TTMapper ttMapper;

    public void create(TTEntity ttEntity){
        int rs = ttMapper.save(ttEntity);
        if(rs < 1){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, ttEntity.toString());
        }
    }

    public List<TTEntity> queryByIds(List<Long> tids){
        TTQO ttqo = new TTQO();
        ttqo.setIds(tids);
        return ttMapper.select(ttqo);
    }
}
