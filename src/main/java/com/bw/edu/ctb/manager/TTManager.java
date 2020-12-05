package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.TTQO;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.mapper.TTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTManager {
    @Autowired
    private TTMapper ttMapper;

    public List<TTEntity> queryByIds(List<Long> tids){
        TTQO ttqo = new TTQO();
        ttqo.setIds(tids);
        return ttMapper.select(ttqo);
    }
}
