package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.dao.entity.ExSttByclEntity;
import com.bw.edu.ctb.dao.mapper.ExSttByclMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExSttByclManager {
    @Autowired
    private ExSttByclMapper exSttByclMapper;

    public void save(ExSttByclEntity ee){
        int rs = exSttByclMapper.save(ee);
        if(rs < 0){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, ee.toString());
        }
    }

    public void update(ExSttByclEntity ee){
        exSttByclMapper.update(ee);
    }

    public ExSttByclEntity selectLatestValidated(ExSttByclQO exSttByclQO){
        return exSttByclMapper.selectLatestValidated(exSttByclQO);
    }

    public List<ExSttByclEntity> select(ExSttByclQO exSttByclQO){
        return exSttByclMapper.select(exSttByclQO);
    }
}
