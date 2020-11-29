package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.TkrQO;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.TkrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TkrManager {
    @Autowired
    private TkrMapper tkrMapper;

    public List<TkrEntity> queryTTs(Long kpid, Integer eok){
        TkrQO tkrQO = new TkrQO();
        tkrQO.setKpId(kpid);
        tkrQO.setEok(eok);
        tkrQO.setSortMode("ASC");
        tkrQO.setSortProperty("tid");
        tkrQO.setBegin(0);
        tkrQO.setNum(20);//最大20条
        List<TkrEntity> tkrEntityList = tkrMapper.select(tkrQO);
        return tkrEntityList;
    }
}