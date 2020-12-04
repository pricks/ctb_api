package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.enums.EokEnum;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.TkrMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TkrDAOTest extends CtbApplicationTests {
    @Autowired
    private TkrMapper tkrMapper;

    @Test
    public void testSave(){
        create(2L, 1L);
        create(2L, 2L);
        create(2L, 3L);

        create(3L, 6L);
        create(3L, 7L);
        create(3L, 8L);

        create(7L, 9L);

        create(8L, 10L);
        create(8L, 11L);

    }

    private void create(Long kpid, Long tid){
        TkrEntity te = new TkrEntity();
        te.setKpid(kpid);
        te.setTid(tid);
        te.setEok(EokEnum.KP_DETAIL.getCode());
        tkrMapper.save(te);
    }
}
