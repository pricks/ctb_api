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
//        titleQO.setGrade(3);
//        titleQO.setClassType(1);
//        titleQO.setType(1);
//        titleQO.setDagang(1);
//        titleQO.setRegion(1);
        titleQO.setSortProperty("gmt_create");
        List<CtbTitleEntity> titleEntityList = ctbTitleMapper.selectByPage(titleQO);
        System.out.println("size="+titleEntityList.size());
    }

    @Test
    public void testGetById(){
        List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getById(1L);
//		List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getAll();
        System.out.println("=======" + titleEntityList.size());
    }

    @Test
    public void testSave(){
        CtbTitleEntity titleEntity = new CtbTitleEntity();
        titleEntity.setShortContent("2221332testtt");
        titleEntity.setCommentsCount(1);
        titleEntity.setAuthorName("system");
        titleEntity.setAuthorId(1L);
        titleEntity.setClassType(1);
        titleEntity.setCovers("");
        titleEntity.setContent("232311223fdsafsagsa");
        titleEntity.setDagang(2);
        titleEntity.setGrade(3);
        titleEntity.setRegion(1);
        titleEntity.setType(1);
        titleEntity.setAnswer("no answer");
        int rs = ctbTitleMapper.save(titleEntity);
        System.out.println("id===" + titleEntity.getId());
    }
}
