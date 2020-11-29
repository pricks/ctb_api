package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class TkrQO extends Paging implements Serializable {
    private Long kpId;
    private Long tid;
    private Integer eok;

    public Long getKpId() {
        return kpId;
    }

    public void setKpId(Long kpId) {
        this.kpId = kpId;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Integer getEok() {
        return eok;
    }

    public void setEok(Integer eok) {
        this.eok = eok;
    }
}
