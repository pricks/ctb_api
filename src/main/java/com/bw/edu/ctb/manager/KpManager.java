package com.bw.edu.ctb.manager;

import com.bw.edu.ctb.common.enums.SortEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.mapper.KpMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KpManager {
    @Autowired
    private KpMapper kpMapper;
    private static final String ORDER_FIELD = "korder";

    public List<KpEntity> query(KpQO kpQO){
        kpQO.setSortProperty(ORDER_FIELD);
        kpQO.setSortMode(SortEnum.ASC.getMode());
        return kpMapper.select(kpQO);
    }

    public List<KpEntity> queryFirstLevel(KpQO kpQO){
        kpQO.setLevel(1);
        kpQO.setSortProperty(ORDER_FIELD);
        kpQO.setSortMode(SortEnum.ASC.getMode());
        return kpMapper.select(kpQO);
    }

    public KpEntity getByIdNotNull(Long kpid){
        KpEntity r = kpMapper.getById(kpid);
        if(null != r){
            return r;
        }
        throw new CtbException(CtbExceptionEnum.KP_IS_NULL);
    }

    /**
     * 查询order比自己大的兄弟节点。注意：level必须相同，因为pid可能为空
     * @param k
     * @return
     */
    public List<KpEntity> querySiblings(KpEntity k){
        KpQO kpQO = new KpQO();
        kpQO.setUn(k.getUn());
        kpQO.setDl(k.getDl());
        kpQO.setPid(k.getPid());
        kpQO.setLevel(k.getLevel());
        kpQO.setKorder(k.getKorder());
        kpQO.setStatus(StatusEnum.PULISHED.getCode());
        return kpMapper.select(kpQO);
    }

    /**
     * 查询子节点，并按照order从小到大排序
     * @param pid
     * @return
     */
    public List<KpEntity> queryOrderdChildren(Long pid){
        KpQO kpQO = new KpQO();
        kpQO.setPid(pid);
        kpQO.setStatus(StatusEnum.PULISHED.getCode());
        kpQO.setSortMode("ASC");
        kpQO.setSortProperty(ORDER_FIELD);
        List<KpEntity> kpEntityList = kpMapper.select(kpQO);
        return kpEntityList;
    }
}
