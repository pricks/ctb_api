package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.qo.KptBatchQO;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.mapper.KptBatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KptBatchManager {
    @Autowired
    private KptBatchMapper kptBatchMapper;

    /**
     * 查询最后一条有效的批次
     * @param kptBatchQO
     * @return
     */
    public KptBatchEntity queryLastValid(KptBatchQO kptBatchQO){
        kptBatchQO.setNum(1);
        kptBatchQO.setStatus(BatchStatusEnum.GENERATED.getCode());
        List<KptBatchEntity> kptBatchEntityList = kptBatchMapper.select(kptBatchQO);

        /**
         * 这里理论上不应该出现多条，出现多条说明有数据不一致
         * 但是这里为了用户体验，不能报错，就返回第一条
         */
        if(null != kptBatchEntityList && kptBatchEntityList.size() > 0){
            return kptBatchEntityList.get(0);
        }
        return null;
    }
}
