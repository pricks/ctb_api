package com.bw.edu.ctb.bizutils;

import com.bw.edu.ctb.common.constants.Symbols;
import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.util.CollectionUtil;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;
import com.bw.edu.ctb.exception.CtbExceptionEnum;

import static com.bw.edu.ctb.exception.CtbExceptionEnum.promoteException;

import java.util.ArrayList;
import java.util.List;

public class KptBatchUtil {
    public static KptBatchEntity genKptBatch(Long uid, Long un, Integer dl, Integer rd, Long maxKpid, List<TkrEntity> tkrs, List<Long> ttids) {
        int sz1=0, sz2=0;
        if(CollectionUtil.isNotEmpty(tkrs)){
            sz1 = tkrs.size();
        }
        if(CollectionUtil.isNotEmpty(ttids)){
            sz2 = ttids.size();
        }
        int size = sz1+sz2;
        if(size==0){
            promoteException(CtbExceptionEnum.TTID_NULL_FOR_BATCH);
        }

        KptBatchEntity kb = new KptBatchEntity();
        kb.setUid(uid);
        kb.setUn(un);
        kb.setDl(dl);
        kb.setRd(rd);
        kb.setMaxk(maxKpid);


        List<Long> tids = new ArrayList<>(size);
        if(sz1>0){
            for(TkrEntity te : tkrs){
                tids.add(te.getTid());
            }
        }
        //将外部传入的title id set加入到batch中
        if(sz2>0){
            tids.addAll(ttids);
        }

        StringBuilder sb = new StringBuilder();
        int tsize = tids.size();
        for (int m=0; m<tsize; m++){
            sb.append(tids.get(m));
            if(m < (tsize-1)){
                sb.append(Symbols.COMMA);
            }
        }
        kb.setTids(sb.toString());

        kb.setMaxt(tids.get(tsize-1));
        kb.setStatus(BatchStatusEnum.GENERATED.getCode());
        return kb;
    }
}
