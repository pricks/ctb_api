package com.bw.edu.ctb.dao.entity;

import lombok.Data;

import java.util.Date;

public class ExRecEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Long dg;
    private Long gd;
    private Long cl;
    private Long un;
    private Long dl;
    private Integer rd;
    private Long batchId;
    private String tts;
    private String kns;
    private String wkns;
    private String wtts;
    private Long cmkn;

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

    public Long getDg() {
        return dg;
    }

    public void setDg(Long dg) {
        this.dg = dg;
    }

    public Long getGd() {
        return gd;
    }

    public void setGd(Long gd) {
        this.gd = gd;
    }

    public Long getCl() {
        return cl;
    }

    public void setCl(Long cl) {
        this.cl = cl;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public Long getDl() {
        return dl;
    }

    public void setDl(Long dl) {
        this.dl = dl;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getKns() {
        return kns;
    }

    public void setKns(String kns) {
        this.kns = kns;
    }

    public String getWkns() {
        return wkns;
    }

    public void setWkns(String wkns) {
        this.wkns = wkns;
    }

    public String getWtts() {
        return wtts;
    }

    public void setWtts(String wtts) {
        this.wtts = wtts;
    }

    public Long getCmkn() {
        return cmkn;
    }

    public void setCmkn(Long cmkn) {
        this.cmkn = cmkn;
    }
}
