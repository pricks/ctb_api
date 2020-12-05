package com.bw.edu.ctb.service;

import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.enums.Symbols;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.common.qo.KptBatchQO;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.common.qo.TkrQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.dao.mapper.KpMapper;
import com.bw.edu.ctb.dao.mapper.TTMapper;
import com.bw.edu.ctb.dao.mapper.TkrMapper;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.KpManager;
import com.bw.edu.ctb.manager.KptBatchManager;
import com.bw.edu.ctb.manager.TTManager;
import com.bw.edu.ctb.manager.TkrManager;
import com.bw.edu.ctb.web.controller.MCController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TTService {
    private final static Logger logger = LoggerFactory.getLogger(TTService.class);
    @Autowired
    private TTManager ttManager;

    @Autowired
    private KpManager kpManager;
    @Autowired
    private TkrManager tkrManager;
    @Autowired
    private KptBatchManager kptBatchManager;

    public Result<List<TTEntity>> queryKpDetails(TTBactchQO ttBactchQO){
        List<TTEntity> ttEntityList = null;
        List<Long> tids = null;

        //查询batch
        KptBatchEntity kb = kptBatchManager.queryLastValid(buildKptBatchQO(ttBactchQO));

        //如果batch查不到，实时生成batch
        if(null == kb){
            kb = new KptBatchEntity();
            tids = queryTTSAndGenBatch(ttBactchQO, kb);
            if(null == tids || tids.size() == 0){
                logger.info("未查询到tt. ttBactchQO="+ttBactchQO);//todo monitor
                return Result.success();//用户友好
            }
        }


        //查询titles
        if(null == kb.getTids() || "".equals(kb.getTids())){
            logger.error("[fatal error] kpt_batch.tids=null");//todo monitor
            return Result.success();
        }
        List<String> tidStrs = Arrays.asList(kb.getTids().split(Symbols.COMMA));
        tids = new ArrayList<>(tidStrs.size());
        for(String str : tidStrs){
            tids.add(Long.valueOf(str));
        }
        List<TTEntity> tts = ttManager.queryByIds(tids);
        if(null==tts || tts.size()==0){
            logger.error("[fatal error] tt=null");
        }

        return Result.success(tts);
    }

    /**
     * 根据批次搜索titles todo 要生成batch
     * @param ttBactchQO
     * @return
     */
    private List<Long> queryTTSAndGenBatch(TTBactchQO ttBactchQO, KptBatchEntity kb){
        Long maxKpId = getMaxKpid(ttBactchQO);
        Long maxTid = ttBactchQO.getMaxTid();//maxTid可以为空
        Integer eok = ttBactchQO.getEok();

        //查询当前kp下的tt，注：这些tt的id必须大于 maxTid
        List<TkrEntity> tkrs = new ArrayList<>();
        KpEntity k = kpManager.getByIdNotNull(maxKpId);
        Long maxKpid = searchKpDetails(k, maxTid, eok, tkrs, true, true);
        if(tkrs.size() == 0){
            return null;//说明当前单元下还没有titles
        }
        int size = tkrs.size();
        if(size > 10){
            tkrs = tkrs.subList(0, 9);
        }
        List<Long> tids = new ArrayList<>(size);
        for(TkrEntity te : tkrs){
            tids.add(te.getTid());
        }

        //生成kptBatch
        genKptBatch(ttBactchQO, kb, maxKpid, tids);

        return tids;
    }

    private void genKptBatch(TTBactchQO ttBactchQO, KptBatchEntity kb, Long maxKpid, List<Long> tids) {
        kb.setUid(ttBactchQO.getUid());
        kb.setUn(ttBactchQO.getUn());
        kb.setDl(ttBactchQO.getDl());
        kb.setMaxKpid(maxKpid);

        StringBuilder sb = new StringBuilder();
        int tsize = tids.size();
        for (int m=0; m<tsize; m++){
            sb.append(tids.get(m));
            if(m < (tsize-1)){
                sb.append(Symbols.COMMA);
            }
        }
        kb.setTids(sb.toString());

        kb.setMaxTid(tids.get(tsize-1));
        kb.setStatus(BatchStatusEnum.GENERATED.getCode());
        kptBatchManager.create(kb);
    }

    private Long getMaxKpid(TTBactchQO ttBactchQO){
        Long maxKpId = ttBactchQO.getMaxKpId();
        if(null == maxKpId){
            Long un = ttBactchQO.getUn();
            Integer dl = ttBactchQO.getDl();
            if(null == un){
                throw new CtbException(CtbExceptionEnum.UNIT_IS_NULL);
            }
            if(null == dl){
                throw new CtbException(CtbExceptionEnum.DL_IS_NULL);
            }

            KpQO kpQO = new KpQO();
            kpQO.setUn(un);
            kpQO.setDl(dl);
            kpQO.setStatus(StatusEnum.PULISHED.getCode());
            kpQO.setNum(1);
            List<KpEntity> kps = kpManager.queryByUn(kpQO);
            if(null==kps || kps.size()==0){
                return null;
            }
            maxKpId = kps.get(0).getId();
        }
        return maxKpId;
    }

    private KptBatchQO buildKptBatchQO(TTBactchQO ttBactchQO) {
        KptBatchQO kq = new KptBatchQO();
        kq.setUid(ttBactchQO.getUid());
        kq.setUn(ttBactchQO.getUn());
        kq.setDl(ttBactchQO.getDl());
        return kq;
    }

    /**
     * 搜索
     * @param k
     * @param maxTid
     * @param eok
     * @param tkrs
     * @param searchParent
     * @param searchForward
     * @return 最后的kp节点ID
     */
    private Long searchKpDetails(KpEntity k, Long maxTid, Integer eok, List<TkrEntity> tkrs, boolean searchParent, boolean searchForward){
        if(null == eok){
            throw new CtbException(CtbExceptionEnum.EOK_IS_NULL);
        }
        Long kpid = k.getId();
        int size = tkrs.size();

        //1.查询当前kp下的tt，如果超过10个，就返回前面10个；否则进入第2步
        List<TkrEntity> kpDetails = tkrManager.queryTTs(k.getId(), eok);
        if(null != kpDetails && kpDetails.size() > 0){
            if(maxTid > 0){
                for(TkrEntity te : kpDetails){
                    if(te.getTid() > maxTid){
                        tkrs.add(te);
                    }
                }
            }else{
                tkrs.addAll(kpDetails);
            }
        }
        if(tkrs.size() >= 10){
            return kpid;
        }


        //2.以当前kp节点为中心，查询其所有子节点的tt；如果还没有足够的tt，就查询当前kp的后续兄弟节点以及兄弟节点的子节点的所有tt
        List<KpEntity> subKps = kpManager.queryOrderdChildren(k.getId());
        if(null != subKps && subKps.size() > 0){
            KpEntity subk = subKps.get(0);
            kpid = searchKpDetails(subk, 0L, eok, tkrs, false, true);
            if(tkrs.size() >= 10){
                return kpid;
            }
        }

        //3.如果没有children，则查询sibling kp，按照order正向排序
        //如果 searchForward==false，则既不搜索兄弟节点，也不搜索父节点
        if(!searchForward) return kpid;
        List<KpEntity> sibkps = kpManager.querySiblings(k);
        if(null != sibkps && sibkps.size() > 0){
            for(KpEntity sibk : sibkps){
                kpid = searchKpDetails(sibk, 0L, eok, tkrs, false, false);
                if(tkrs.size() >= 10){
                    return kpid;
                }
            }
        }

        //4.如果当前kp节点所在level的所有纵向节点下的tt数量还不够，就获取当前节点的父节点的后续第一个兄弟节点继续查询
        if(!searchParent) return kpid;
        if(null == k.getPid()) return kpid;
        KpEntity pk = kpManager.getByIdNotNull(k.getPid());
        List<KpEntity> siblings = kpManager.querySiblings(pk);
        if(null == siblings || siblings.size() == 0){
            return kpid;
        }
        return searchKpDetails(siblings.get(0), 0L, eok, tkrs, true,true);
    }
}
