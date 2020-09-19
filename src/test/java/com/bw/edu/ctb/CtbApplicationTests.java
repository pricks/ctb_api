package com.bw.edu.ctb;

import com.bw.edu.ctb.dao.entity.CtbTitleEntity;
import com.bw.edu.ctb.dao.mapper.CtbTitleMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CtbApplication.class)
public abstract class CtbApplicationTests {
	@Autowired
	private CtbTitleMapper ctbTitleMapper;

//	@Test
	public void testGetById(){
		List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getById(1L);
//		List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getAll();
		System.out.println("=======" + titleEntityList.size());
	}

//	@Test
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

	@Test
	void contextLoads() {
	}

}
