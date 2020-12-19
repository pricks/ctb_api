package com.bw.edu.ctb.bizutils;

import com.bw.edu.ctb.common.constants.Symbols;
import com.bw.edu.ctb.common.enums.BatchStatusEnum;
import com.bw.edu.ctb.common.qo.TTBactchQO;
import com.bw.edu.ctb.dao.entity.KptBatchEntity;
import com.bw.edu.ctb.dao.entity.TkrEntity;

import java.util.ArrayList;
import java.util.List;

public class KptBatchUtil {
    public static KptBatchEntity genKptBatch(Long uid, Long un, Integer dl, Long maxKpid, List<TkrEntity> tkrs) {
        if(null==tkrs || tkrs.size()==0){
            return null;
        }
        KptBatchEntity kb = new KptBatchEntity();
        kb.setUid(uid);
        kb.setUn(un);
        kb.setDl(dl);
        kb.setMaxKpid(maxKpid);

        int size = tkrs.size();
        if(size > 10){
            tkrs = tkrs.subList(0, 9);
        }
        List<Long> tids = new ArrayList<>(size);
        for(TkrEntity te : tkrs){
            tids.add(te.getTid());
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

        kb.setMaxTid(tids.get(tsize-1));
        kb.setStatus(BatchStatusEnum.GENERATED.getCode());
        return kb;
    }
}
