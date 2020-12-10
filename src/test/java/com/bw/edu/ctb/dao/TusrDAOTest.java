package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.qo.usr.TUsrQO;
import com.bw.edu.ctb.dao.entity.usr.TUsr;
import com.bw.edu.ctb.dao.mapper.UtsrMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TusrDAOTest extends CtbApplicationTests {
    @Autowired
    private UtsrMapper tUsrMapper;

    @Test
    public void testSave(){
        TUsr u = new TUsr();
        u.setUid(1L);
        u.setNick("azz");
        u.setOid("121fesa");
        u.setAtk("fsajfoepwugi");
        u.setType(1);
        int rs = tUsrMapper.save(u);
        System.out.println("====rs="+rs+", id="+u.getId());
    }

    @Test
    public void testQueryByNick(){
        TUsrQO q = new TUsrQO();
        q.setNick("azz");
        q.setType(1);
        List<TUsr> usrList = tUsrMapper.select(q);
        System.out.println("===size="+usrList.size());
    }
}
