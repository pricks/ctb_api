package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.dao.mapper.ExRecMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExRecManager {
    @Autowired
    private ExRecMapper exRecMapper;

    public void save(ExRecEntity e){
        int rs = exRecMapper.save(e);
        if(rs < 1){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, e.toString());
        }
    }

    /**
     * 查询用户的最后一次练习记录
     * @param uid
     * @return
     */
    public Long selectLatestExr(Long uid){
        return exRecMapper.selectLatestExr(uid);
    }
}
