package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.qo.UnitQO;
import com.bw.edu.ctb.dao.entity.UnitEntity;
import com.bw.edu.ctb.dao.mapper.UnitMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UnitDAOTest extends CtbApplicationTests {
    @Autowired
    private UnitMapper unitMapper;

    @Test
    public void testSave(){
        UnitEntity ue = new UnitEntity();
        ue.setGc(new Date());
        ue.setGm(new Date());
        ue.setDg(1);
        ue.setGd(1);
        ue.setCl(2);
        ue.setBl("xxx");
        ue.setCode(1L);
        ue.setName("unit one");
        Integer rs = unitMapper.save(ue);
        System.out.println("===rs: "+rs+", id="+ue.getId());
    }

    @Test
    public void testSelectByPage(){
        UnitQO uq = new UnitQO();
        uq.setGd(1);
        uq.setCl(2);
        List<UnitEntity> unitEntityList = unitMapper.selectByPage(uq);
        System.out.println("========size="+unitEntityList.size());
    }
}
