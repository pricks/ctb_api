package com.bw.edu.ctb.web.controller.common;

import com.bw.edu.ctb.dao.entity.TTEntity;
import com.bw.edu.ctb.domain.EBatch;
import com.bw.edu.ctb.domain.EBatchTT;

import java.util.ArrayList;
import java.util.List;

public class TTBuilder {

    public static EBatch build(Long kpId, Integer dl, List<TTEntity> tts){
        EBatch eb = new EBatch();
        eb.setEb_id(kpId);
        eb.setKp_dl(dl);

        List<EBatchTT> eBatchTTList = new ArrayList<>();
        int size = tts.size();
        eb.setTt_count(size);
        eb.setTt_g_merge_count(size);//todo 这里后面要改成真正的值
        for(int k=0; k<size; k++){
            TTEntity tt = tts.get(k);
            EBatchTT et = new EBatchTT();
            et.setTt_g(false);//todo 这里要真正进行判断
            et.setT_idx(k+1);
            et.setTid(tt.getId());
            et.setTt_ct(tt.getTc());
            et.setDl(tt.getDl());
            et.setTt_offline(tt.getOi());
            et.setTt_type(tt.getType());
            et.setT_ops(tt.getOts());
            et.setTt_answer(tt.getTca());
            et.setTat(tt.getTat());
            et.setT_g_count(0);//todo
            eBatchTTList.add(et);
        }
        eb.setTt_list(eBatchTTList);
        return eb;
    }
}
