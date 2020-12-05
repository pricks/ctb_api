package com.bw.edu.ctb.dao.entity;

import java.util.Date;

public class KptBatchEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Long un;
    private Integer dl;
    private Long maxKpid;
    private String tids;
    private Long maxTid;
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

    public Long getMaxKpid() {
        return maxKpid;
    }

    public void setMaxKpid(Long maxKpid) {
        this.maxKpid = maxKpid;
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    public Long getMaxTid() {
        return maxTid;
    }

    public void setMaxTid(Long maxTid) {
        this.maxTid = maxTid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
