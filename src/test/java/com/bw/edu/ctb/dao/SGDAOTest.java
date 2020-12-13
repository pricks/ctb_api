package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.enums.subjects.DagangEnum;
import com.bw.edu.ctb.common.enums.subjects.GradeEnum;
import com.bw.edu.ctb.dao.entity.SGEntity;
import com.bw.edu.ctb.dao.mapper.SGMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SGDAOTest extends CtbApplicationTests {
    @Autowired
    private SGMapper sgMapper;

    @Test
    public void testCreate(){
        create(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X1.getCode(), 1, "语文");
        create(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X1.getCode(), 2, "小古文");
        create(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X1.getCode(), 3, "数学");
        create(DagangEnum.RENJIAOBAN.getCode(), GradeEnum.X1.getCode(), 4, "口算");
    }

    private void create(int dg, int gd, int sc, String sn){
        SGEntity sg = new SGEntity();
        sg.setDg(dg);
        sg.setGd(gd);
        sg.setSc(sc);
        sg.setSn(sn);
        int rs = sgMapper.save(sg);
        System.out.println("====rs="+rs+", id="+sg.getId());
    }
}
