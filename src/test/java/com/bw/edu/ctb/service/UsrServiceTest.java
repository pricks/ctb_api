package com.bw.edu.ctb.service;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.domain.LUsrE;
import com.bw.edu.ctb.service.usr.UsrService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsrServiceTest extends CtbApplicationTests {
    @Autowired
    private UsrService usrService;

    @Test
    public void createNewLUsrTest(){
        LUsrE lUsrE = new LUsrE();
        lUsrE.setPhone("18600000000");
        lUsrE.setPwd("123456");
        lUsrE.setAts("b=Apple, m=iPhone6s Plus, h=736, di=undefined");
        usrService.createNewLUsr(lUsrE);
    }
}
