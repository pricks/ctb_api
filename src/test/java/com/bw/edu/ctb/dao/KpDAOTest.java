package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.mapper.KpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KpDAOTest extends CtbApplicationTests {
    @Autowired
    private KpMapper kpMapper;

    @Test
    public void testSelect(){
        KpQO kpQO = new KpQO();
        kpQO.setUn(1L);
        List<KpEntity> kpEntityList = kpMapper.select(kpQO);
        System.out.println("=======size="+kpEntityList.size());
    }

    @Test
    public void testSave(){
        Long kp1 = create(null, 1, 1, "生字");
        create(kp1, 2, 1, "春");
        create(kp1, 2, 2, "夏");
        create(kp1, 2, 3, "秋");
        create(kp1, 2, 4, "冬");
        create(kp1, 2, 5, "季");

        Long kp2 = create(null, 1, 1, "组词");
        create(kp2, 2, 1, "春天");
        create(kp2, 2, 2, "夏日");
        create(kp2, 2, 3, "秋高气爽");
        create(kp2, 2, 4, "冬季");
        create(kp2, 2, 5, "季节");
        create(kp2, 2, 6, "绿树");
        create(kp2, 2, 7, "小河");
    }

    private Long create(Long pid, Integer level, Integer korder, String point){
        KpEntity k = new KpEntity();
        k.setUn(1L);
        k.setDl(DlEnum.BASIC.getCode());
        k.setPid(pid);
        k.setLevel(level);
        k.setKorder(korder);
        k.setPoint(point);
        k.setStatus(StatusEnum.PULISHED.getCode());
        int rs = kpMapper.save(k);
        if(rs <= 0){
            throw new RuntimeException("保存失败");
        }
        return k.getId();
    }
}
