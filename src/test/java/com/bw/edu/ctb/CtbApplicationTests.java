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
class CtbApplicationTests {
	@Autowired
	private CtbTitleMapper ctbTitleMapper;

	@Test
	public void testGetById(){
//		List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getById(1L);
		List<CtbTitleEntity> titleEntityList = ctbTitleMapper.getAll();
		System.out.println("=======" + titleEntityList.size());
	}

	@Test
	void contextLoads() {
	}

}
