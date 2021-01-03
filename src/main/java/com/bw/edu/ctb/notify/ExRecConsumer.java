package com.bw.edu.ctb.notify;

import com.bw.edu.ctb.bizutils.KptBatchUtil;
import com.bw.edu.ctb.common.constants.Symbols;
import com.bw.edu.ctb.common.constants.SystemConstants;
import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.enums.KptBatchStatusEnum;
import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.common.util.JacksonUtil;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.domain.SttClDO;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.*;
import com.bw.edu.ctb.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

@Service
public class ExRecConsumer {
    private final static Logger logger = LoggerFactory.getLogger(ExRecConsumer.class);
    @Autowired
    private ExRecManager exRecManager;
    @Autowired
    private KptBatchManager kptBatchManager;
    @Autowired
    private UnitManager unitManager;
    @Autowired
    private ExSttByclManager exSttByclManager;
    @Autowired
    private TsearchMnager tsearchMnager;

    public void consume(Long erid){
        logger.error("erid="+erid);
        try {
            ExRecEntity ee = exRecManager.getById(erid);
            if(null==ee){
                logger.error("没有查询到ex rec. erid="+erid);
                return;
            }

            //gen batch
            KptBatchEntity kb = genKptBatch(ee);

            //update ex_stt_bycl
            ExSttByclEntity ebe = updateExStt(ee, kb);

            //gen wrong_rec todo 先不做

            //write db
            writeDB(ee, kb, ebe);

        }catch (CtbException e){
            logger.error("consume failed. erid="+erid, e);
        } catch(Exception e){
            logger.error("consume failed because system. erid="+erid, e);
        }//这里不能抛错，不然会让blockedQueue卡住，一直消费不了
    }

    /**
     * 将纯粹写db的操作封装在本方法里
     */
    @Transactional(transactionManager = "basicTransactionManager", rollbackFor = Throwable.class)
    private void writeDB(ExRecEntity ee, KptBatchEntity kb, ExSttByclEntity ebe){
        kptBatchManager.updateStatus(ee.getBid(), KptBatchStatusEnum.COMMITED.getCode(), KptBatchStatusEnum.GEN_BATCH.getCode());
        if(null!=kb) kptBatchManager.create(kb);
        if(null!=ebe){
            exSttByclManager.update(ebe);
        }
    }

    private ExSttByclEntity updateExStt(ExRecEntity ee, KptBatchEntity kb){
        Long uid = ee.getUid();
        Long un = ee.getUn();

        UnitEntity ue = unitManager.getByCode(un);
        if(null==ue){
            promoteException(CtbExceptionEnum.UNIT_IS_NULL, "un="+un);
        }

        Integer cl = ue.getCl();
        Integer gd = ue.getGd();
        Integer dg = ue.getDg();

        ExSttByclQO exSttByclQO = new ExSttByclQO();
        exSttByclQO.setDg(dg);
        exSttByclQO.setGd(gd);
        exSttByclQO.setCl(cl);
        exSttByclQO.setUid(uid);
        ExSttByclEntity exSttByclEntity = exSttByclManager.selectLatestValidated(exSttByclQO);
        if(null==exSttByclEntity){
            promoteException(CtbExceptionEnum.EX_STT_BYCL_IS_NULL, "un="+un);
        }
        if(StringUtil.isEmpty(exSttByclEntity.getStt())){
            promoteException(CtbExceptionEnum.EX_STT_BYCL_CONT_NULL, "un="+un);
        }
        try {
            SttClDO stt = JacksonUtil.deserialize(exSttByclEntity.getStt(), SttClDO.class);
            stt.update(ee, kb);
            exSttByclEntity.setStt(JacksonUtil.serialize(stt));
            return exSttByclEntity;
        } catch (Exception e) {
            logger.error("[fatal] 反序列化失败. exSttByclId="+exSttByclEntity.getId());//TODO monitor
            promoteException(CtbExceptionEnum.DESERIALIZE_FAILED, "exSttByclId="+exSttByclEntity.getId());
        }
        return exSttByclEntity;
    }


    /**
     * 仅创建kb实例，不写DB
     * @param er
     * @return
     */
    private KptBatchEntity genKptBatch(ExRecEntity er){
        //新生成的batch中，需要保留上次做错的题目
        int maxNum = SystemConstants.MAX_TTS_NUM;
        List<Long> wttList = null;
        String wtts = er.getWtts();
        if(StringUtil.isNotEmpty(wtts)){
            List<String> stl = Arrays.asList(er.getWtts().split(Symbols.COMMA));
            wttList = CollectionUtil.transfer(stl);
            maxNum = SystemConstants.MAX_TTS_NUM-wttList.size();
        }

        List<TkrEntity> tkrs = new ArrayList<>();
        Long maxKpid = tsearchMnager.searchKpDetails(er.getMaxk(), er.getMaxt(), DlEnum.getEokType(er.getDl()).getCode(), maxNum, tkrs);
        if(tkrs.size() == 0 && CollectionUtil.isEmpty(wttList)){
            return null;//说明当前单元下还没有titles，并且也没有历史错题
        }
        KptBatchEntity kb = KptBatchUtil.genKptBatch(er.getUid(), er.getUn(), er.getDl(), er.getRd(), maxKpid, tkrs, wttList);
        return kb;
    }
}
