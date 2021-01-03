package com.bw.edu.ctb.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class ExRecEntity {
    private Long id;
    private Date gc;
    private Long uid;
    private Long un;
    private Integer rd;
    private Integer dl;
    private Long bid;//kptBatch or pracBatch
    private String tts;
    private String kns;
    private String wkns;//wrong-answered kn id set
    private String wtts;//wrong-answered tt id set
    private Integer score;
    private Integer ckc;//correct kp count
    private Long maxk;//max id of kp
    private Long maxt;//max id of tt

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

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public Long getBid() {
        return bid;
    }

    public void setBid(Long bid) {
        this.bid = bid;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCkc() {
        return ckc;
    }

    public void setCkc(Integer ckc) {
        this.ckc = ckc;
    }

    public Long getMaxk() {
        return maxk;
    }

    public void setMaxk(Long maxk) {
        this.maxk = maxk;
    }

    public Long getMaxt() {
        return maxt;
    }

    public void setMaxt(Long maxt) {
        this.maxt = maxt;
    }
}
