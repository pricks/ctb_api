package com.bw.edu.ctb.dao.entity;

import java.util.Date;

public class KptBatchEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Long un;
    private Integer rd;
    private Integer dl;
    private Long maxk;
    private String tids;
    private Long maxt;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGc() {
        return gc;
    }

    public void setGc(Date gc) {
        this.gc = gc;
    }

    public Date getGm() {
        return gm;
    }

    public void setGm(Date gm) {
        this.gm = gm;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

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

    public Long getMaxk() {
        return maxk;
    }

    public void setMaxk(Long maxk) {
        this.maxk = maxk;
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    public Long getMaxt() {
        return maxt;
    }

    public void setMaxt(Long maxt) {
        this.maxt = maxt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }
}
