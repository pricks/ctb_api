package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.enums.SortEnum;
import com.bw.edu.ctb.common.qo.CtbTtQO;
import com.bw.edu.ctb.dao.entity.CtbTtEntity;
import com.bw.edu.ctb.dao.mapper.CtbTtMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CtbTtDAOTest extends CtbApplicationTests {
    @Autowired
    private CtbTtMapper ctbTtMapper;

    @Test
    public void testSave(){
        CtbTtEntity tt = new CtbTtEntity();
        tt.setUid(1L);
        tt.setSrc(1);
        tt.setErrc(1);
        tt.setTid(5L);
        tt.setUn(12L);
        int rs = ctbTtMapper.save(tt);
        System.out.println("====rs: "+rs);
    }

    @Test
    public void query() throws ParseException {
        CtbTtQO qo = new CtbTtQO();
        qo.setUid(17L);
//        qo.setMaxGm("2021-06-12 10:01:34");
        qo.setUn(1221L);
        qo.setNum(2);
        qo.setSortProperty("gm");
        qo.setSortMode(SortEnum.ASC.getMode());
        List<CtbTtEntity> ttEntities = ctbTtMapper.query(qo);
        System.out.println("=====list size="+ttEntities.size());
    }

    @Test
    public void countToday(){
        CtbTtQO qo = new CtbTtQO();
        qo.setUid(17L);
        qo.setGc("2021-06-12 10:01:01");
        int c = ctbTtMapper.countToday(qo);
        System.out.println("======c="+c);
    }
}
