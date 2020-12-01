package com.bw.edu.ctb.domain;

import com.bw.edu.ctb.common.enums.DlEnum;
import com.bw.edu.ctb.dao.entity.UnitEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SttClDO implements Serializable {
    private Integer cl;
    private List<SttUnDO> tops;
    private List<SttUnDO> uns;

    public static SttClDO buildEmpty(List<UnitEntity> ues){
        if(null==ues || ues.size()==0) return null;
        SttClDO cl = new SttClDO();
        cl.setCl(ues.get(0).getCl());

        List<SttUnDO> uns = new ArrayList<>(ues.size());
        for(UnitEntity ue: ues){
            SttUnDO un = new SttUnDO();
            un.setRd(1);
            un.setUn(ue.getCode());
            un.setUname(ue.getName());

            List<SttDlDO> dls = new ArrayList<>(DlEnum.size());
            for(DlEnum de : DlEnum.values()){
                SttDlDO d = SttDlDO.newInstance().dl(de.getCode())
                                .dlname(de.getName()).tkp(100)
                                .rkp(0).ekp(0);
                if(de.getCode().equals(DlEnum.BASIC.getCode())){
                    d.active(true);
                }
                if(de.getCode().equals(DlEnum.EXERCISE.getCode())){
                    d.dr(0);
                }
                dls.add(d);
            }
            un.setDls(dls);

            uns.add(un);
        }

        cl.setUns(uns);
        return cl;
    }

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public List<SttUnDO> getTops() {
        return tops;
    }

    public void setTops(List<SttUnDO> tops) {
        this.tops = tops;
    }

    public List<SttUnDO> getUns() {
        return uns;
    }

    public void setUns(List<SttUnDO> uns) {
        this.uns = uns;
    }
}
