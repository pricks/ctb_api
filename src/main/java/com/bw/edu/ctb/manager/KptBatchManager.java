package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.qo.KptBatchQO;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.mapper.KptBatchMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KptBatchManager {
    @Autowired
    private KptBatchMapper kptBatchMapper;

    public void create(KptBatchEntity kb){
        int rs = kptBatchMapper.save(kb);
        if(rs < 0){
            throw new CtbException(CtbExceptionEnum.DB_WRITE_ERROR, kb.toString());
        }
    }

    public void updateStatus(Long id, Integer oriStatus, Integer tarStatus){
        kptBatchMapper.updateStatus(id, oriStatus, tarStatus);
    }

    public KptBatchEntity selectById(Long id){
        return kptBatchMapper.selectById(id);
    }

    /**
     * 查询最后一条有效的批次
     * @param kptBatchQO
     * @return
     */
    public KptBatchEntity queryLastValid(KptBatchQO kptBatchQO){
        kptBatchQO.setNum(1);
        List<KptBatchEntity> kptBatchEntityList = kptBatchMapper.select(kptBatchQO);

        /**
         * 这里理论上不应该出现多条，出现多条说明有数据不一致
         * 但是这里为了用户体验，不能报错，就返回第一条
         */
        if(null != kptBatchEntityList && kptBatchEntityList.size() > 0){
            KptBatchEntity kb = kptBatchEntityList.get(0);
            if(null == kb.getTids() || "".equals(kb.getTids())){
                throw new CtbException(CtbExceptionEnum.ANSWER_IS_NULL);
            }
            return kb;
        }
        return null;
    }
}
