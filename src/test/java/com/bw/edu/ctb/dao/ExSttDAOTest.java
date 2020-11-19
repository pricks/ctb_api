package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.qo.ExSttQO;
import com.bw.edu.ctb.dao.entity.ExSttEntity;
import com.bw.edu.ctb.dao.mapper.ExSttMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ExSttDAOTest extends CtbApplicationTests {
    @Autowired
    private ExSttMapper exSttMapper;

    @Test
    public void testSave(){
        ExSttEntity exSttEntity = new ExSttEntity();
        exSttEntity.setGm(new Date());
        exSttEntity.setGc(new Date());
        exSttEntity.setUid(1L);
        exSttEntity.setUn(3L);
        exSttEntity.setDl(1);
        exSttEntity.setRd(1);
        exSttEntity.setTpg(10);
        exSttEntity.settKps("1,2,3,11");
        exSttEntity.setStatus(1);
        Integer rs = exSttMapper.save(exSttEntity);
        System.out.println("===rs: "+rs+", id="+exSttEntity.getId());
    }

    @Test
    public void testQuery(){
        ExSttQO exSttQO = new ExSttQO();
        exSttQO.setUid(1L);
        exSttQO.setUn(1L);
        exSttQO.setStatus(1);
        List<ExSttEntity> exSttEntityList = exSttMapper.selectByPage(exSttQO);
        System.out.println("====size: "+exSttEntityList.size());
    }
}
