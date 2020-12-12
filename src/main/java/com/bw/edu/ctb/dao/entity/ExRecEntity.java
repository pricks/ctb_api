package com.bw.edu.ctb.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class ExRecEntity {
    private Long id;
    private Date gc;
    private Long uid;
    private Long un;
    private Integer rd;
    private Long dl;
    private Long batchId;//kptBatch or pracBatch
    private String tts;
    private String kns;
    private String wKns;//wrong-answered kn id set
    private String wTts;//wrong-answered tt id set
    private Long cmt;//correct_max_tid
    private Long cmkn;//correct_max_kn

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

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

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    public Long getDl() {
        return dl;
    }

    public void setDl(Long dl) {
        this.dl = dl;
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

    public String getwKns() {
        return wKns;
    }

    public void setwKns(String wKns) {
        this.wKns = wKns;
    }

    public String getwTts() {
        return wTts;
    }

    public void setwTts(String wTts) {
        this.wTts = wTts;
    }

    public Long getCmt() {
        return cmt;
    }

    public void setCmt(Long cmt) {
        this.cmt = cmt;
    }

    public Long getCmkn() {
        return cmkn;
    }

    public void setCmkn(Long cmkn) {
        this.cmkn = cmkn;
    }
}
