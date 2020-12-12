package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.dao.entity.ExRecEntity;
import com.bw.edu.ctb.dao.mapper.ExRecMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExRecDAOTest extends CtbApplicationTests {
    @Autowired
    private ExRecMapper exRecMapper;

    @Test
    public void testCreate(){
        ExRecEntity e = new ExRecEntity();
        e.setUid(1L);
        e.setUn(1L);
        e.setRd(1);
        e.setDl(2L);
        e.setBatchId(1L);
        e.setTts("1,2,3,5");
        e.setKns("3,6,6,7");
        e.setCmkn(3L);
        e.setCmt(36L);
        int rs = exRecMapper.save(e);
        System.out.println("=====rs="+rs+", id="+e.getId());
    }

    @Test
    public void testSelectLatestExr(){
        Long un = exRecMapper.selectLatestExr(1L);
        System.out.println("====un="+un);
    }
}
