package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.qo.TTQO;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.mapper.TTMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TTDAOTest extends CtbApplicationTests {
    @Autowired
    private TTMapper ttMapper;

    @Test
    public void testSave(){
        TTEntity ttEntity = new TTEntity();
        ttEntity.setUn(1L);
        ttEntity.setDl(2);
        ttEntity.setTc("3*3-7=");
        ttEntity.setTs(1);
        ttEntity.setTType(1);
        ttEntity.setEok(1);
        ttEntity.setOi(false);
        ttEntity.setTgi(3L);
        ttEntity.setAid(33L);
        int rs = ttMapper.save(ttEntity);
        System.out.println("=====rs="+rs+", id="+ttEntity.getId());
    }

    @Test
    public void testSelect(){
        TTQO ttqo = new TTQO();
        ttqo.setUn(1L);
        ttqo.setDl(2);
        ttqo.setEok(1);
        List<TTEntity> ttEntityList = ttMapper.select(ttqo);
        System.out.println("=====size="+ttEntityList.size());
    }
}
