package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.TkrQO;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.TkrMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TkrManager {
    @Autowired
    private TkrMapper tkrMapper;

    public void create(TkrEntity tkr){
        int rs = tkrMapper.save(tkr);
        if(rs < 1){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, tkr.toString());
        }
    }

    public List<TkrEntity> queryTTs(Long kpid, Integer eok, int maxNum){
        TkrQO tkrQO = new TkrQO();
        tkrQO.setKpid(kpid);
        tkrQO.setEok(eok);
        tkrQO.setSortMode("ASC");
        tkrQO.setSortProperty("tid");
        tkrQO.setBegin(0);
        tkrQO.setNum(maxNum>200?200:maxNum);//最大200条
        List<TkrEntity> tkrEntityList = tkrMapper.select(tkrQO);
        return tkrEntityList;
    }
}
