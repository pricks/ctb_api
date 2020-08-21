package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.qo.TitleQO;
import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.mapper.CtbTitleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CtbTitleDAOTest extends CtbApplicationTests {
    @Autowired
    private CtbTitleMapper ctbTitleMapper;

    @Test
    public void testQeuryByPage(){
        TitleQO titleQO = new TitleQO();
        titleQO.setGrade(3);
        titleQO.setClassType(1);
//        titleQO.setType(1);
//        titleQO.setDagang(1);
//        titleQO.setRegion(1);
        titleQO.setSortProperty("gmt_create");
        List<CtbTitleEntity> titleEntityList = ctbTitleMapper.selectByPage(titleQO);
        System.out.println("size="+titleEntityList.size());
    }
}
