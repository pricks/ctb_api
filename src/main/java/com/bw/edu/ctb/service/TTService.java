package com.bw.edu.ctb.service;

import com.bw.edu.ctb.bizutils.KptBatchUtil;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.Result;
import com.bw.edu.ctb.common.constants.Keys;
import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.enums.StatusEnum;
import com.bw.edu.ctb.common.constants.Symbols;
import com.bw.edu.ctb.common.qo.KpQO;
import com.bw.edu.ctb.common.qo.KptBatchQO;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.dao.entity.KpEntity;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TTService {
    private final static Logger logger = LoggerFactory.getLogger(TTService.class);
    @Autowired
    private TTManager ttManager;
    @Autowired
    private TsearchMnager tsearchMnager;
    @Autowired
    private KpManager kpManager;
    @Autowired
    private TkrManager tkrManager;
    @Autowired
    private KptBatchManager kptBatchManager;

    public Result<List<TTEntity>> queryKpDetails(TTBactchQO ttBactchQO){
        List<TTEntity> ttEntityList = null;

        //查询batch
        KptBatchEntity kb = kptBatchManager.queryLastValid(buildKptBatchQO(ttBactchQO));

        //如果batch查不到，实时生成batch
        if(null == kb){
            kb = queryTTSAndGenBatch(ttBactchQO);
            if(null==kb){
                logger.info("未查询到tt. ttBactchQO="+ttBactchQO);//todo monitor
                return Result.success();//用户友好
            }
        }

        //查询titles
        List<TTEntity> tts = ttManager.queryByIds(CollectionUtil.transfer(Arrays.asList(kb.getTids().split(Symbols.COMMA))));
        if(null==tts || tts.size()==0){
            logger.error("[fatal error] tt=null");
            return Result.success();
        }

        return Result.success(tts).putAttr(Keys.KPT_BATCH_ID, kb.getId().toString());
    }

    public Result<List<TkrEntity>> searchTTs(Long kpid, Long maxTid, Integer eok, Integer maxNum){
        try {
            List<TkrEntity> tkrs = new ArrayList<>();
            Long maxKpid = tsearchMnager.searchKpDetails(kpid, maxTid, eok, maxNum, tkrs);
            return Result.success(tkrs).putAttr("maxKpid", maxKpid.toString());
        }catch (CtbException e){
            logger.error("searchTTs failed. kpid="+kpid+", maxTid="+maxTid+", eok="+eok);
            return Result.failure(e);
        } catch(Exception e){
            logger.error("searchTTs failed because system. kpid="+kpid+", maxTid="+maxTid+", eok="+eok);
            return Result.failure();
        }
    }

    public Result<List<TkrEntity>> searchTTs(Long kpid, Long maxTid, Integer eok){
        return searchTTs(kpid, maxTid, eok, 10);
    }

    /**
     * 根据批次搜索titles todo 要生成batch
     * @param ttBactchQO
     * @return
     */
    private KptBatchEntity queryTTSAndGenBatch(TTBactchQO ttBactchQO){
        Long maxKpId = getMaxKpid(ttBactchQO);
        Long maxTid = ttBactchQO.getMaxTid();//maxTid可以为空
        Integer eok = ttBactchQO.getEok();

        //查询当前kp下的tt，注：这些tt的id必须大于 maxTid
        List<TkrEntity> tkrs = new ArrayList<>();
        Long maxKpid = tsearchMnager.searchKpDetails(maxKpId, maxTid, eok, SystemConstants.MAX_TTS_NUM, tkrs);
        if(tkrs.size() == 0){
            return null;//说明当前单元下还没有titles
        }
        KptBatchEntity kb = KptBatchUtil.genKptBatch(ttBactchQO.getUid(), ttBactchQO.getUn(), ttBactchQO.getDl(), maxKpid, tkrs, null);

        //生成kptBatch
        kptBatchManager.create(kb);
        return kb;
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

}
