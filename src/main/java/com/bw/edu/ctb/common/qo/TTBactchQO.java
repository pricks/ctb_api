package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class TTBactchQO implements Serializable {
    private Long un;
    private Integer dl;
    private Integer eok;
    private Long maxKpId;
    private Long maxTid;

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public Integer getEok() {
        return eok;
    }

    public void setEok(Integer eok) {
        this.eok = eok;
    }

    public Long getMaxKpId() {
        return maxKpId;
    }

    public void setMaxKpId(Long maxKpId) {
        this.maxKpId = maxKpId;
    }

    public Long getMaxTid() {
        return maxTid;
    }

    public void setMaxTid(Long maxTid) {
        this.maxTid = maxTid;
    }
}