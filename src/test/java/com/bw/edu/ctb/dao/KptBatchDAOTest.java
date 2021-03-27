package com.bw.edu.ctb.dao;

import com.bw.edu.ctb.CtbApplicationTests;
import com.bw.edu.ctb.common.enums.KptBatchStatusEnum;
import com.bw.edu.ctb.dao.mapper.KptBatchMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class KptBatchDAOTest extends CtbApplicationTests {
    @Autowired
    private KptBatchMapper kptBatchMapper;

    @Test
    public void testUpdate(){
        kptBatchMapper.updateStatus(57L, KptBatchStatusEnum.COMMITED.getCode(), KptBatchStatusEnum.CREATED.getCode());
    }
}
