package com.bw.edu.ctb.notify;

import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.common.enums.KptBatchStatusEnum;
import com.bw.edu.ctb.common.qo.ExSttByclQO;
import com.bw.edu.ctb.dao.entity.*;
import com.bw.edu.ctb.exception.CtbException;
import com.bw.edu.ctb.exception.CtbExceptionEnum;
import com.bw.edu.ctb.manager.*;
import com.bw.edu.ctb.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            ExRecEntity er = exRecManager.getById(erid);
            if(null==er){
                logger.error("没有查询到ex rec. erid="+erid);
                return;
            }


            //gen batch
            KptBatchEntity kb = genKptBatch(er);

            //update ex_stt_bycl
            updateExStt(kb);

            //gen wrong_rec


            //write db, in one trx
            kptBatchManager.updateStatus(er.getBatchId(), KptBatchStatusEnum.COMMITED.getCode(), KptBatchStatusEnum.GEN_BATCH.getCode());
            kptBatchManager.create(kb);
//            update_exstt_bycl
        }catch (CtbException e){
            logger.error("consume failed. erid="+erid, e);
        } catch(Exception e){
            logger.error("consume failed because system. erid="+erid, e);
        }//这里不能抛错，不然会让blockedQueue卡住，一直消费不了
    }

    private void updateExStt(KptBatchEntity kb){
        Long uid = kb.getUid();
        Long un = kb.getUn();

        UnitEntity ue = unitManager.getByCode(un);
        if(null==ue){
            promoteException(CtbExceptionEnum.UNIT_IS_NULL, "un="+un);
        }

        Integer cl = ue.getCl();
        Integer gd = ue.getGd();
        Integer dg = ue.getDg();

        ExSttByclQO exSttByclQO = new ExSttByclQO();
//        exSttByclQO.setDg();
//        exSttByclQO.setGd();
        exSttByclQO.setCl(cl);
        exSttByclQO.setUid(uid);
        ExSttByclEntity exSttByclEntity = exSttByclManager.selectLatestValidated(exSttByclQO);
        if(null==exSttByclEntity){
            promoteException(CtbExceptionEnum.EX_STT_BYCL_IS_NULL, "un="+un);
        }
        if(StringUtil.isEmpty(exSttByclEntity.getStt())){

        }

    }


    private KptBatchEntity genKptBatch(ExRecEntity er){
        KptBatchEntity kb = new KptBatchEntity();
        kb.setUid(er.getUid());
        kb.setUn(er.getUn());
        kb.setDl(er.getDl());
        kb.setStatus(KptBatchStatusEnum.CREATED.getCode());

        List<TkrEntity> tkrs = new ArrayList<>();
        tsearchMnager.searchKpDetails(er.getMaxk(), er.getMaxt(), DlEnum.getEokType(er.getDl()).getCode(),tkrs);

        kb.setMaxKpid(er.getMaxk());
        kb.setMaxTid(er.getMaxt());
        kb.setTids(er.getTts());
        return kb;
    }
}
