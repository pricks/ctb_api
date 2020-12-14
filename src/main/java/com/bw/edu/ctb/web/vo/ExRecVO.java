package com.bw.edu.ctb.web.vo;

import java.io.Serializable;

public class ExRecVO implements Serializable {
    private String atk;
    private Integer rd;
    private Long un;
    private Integer dl;
    private Long kptBatch;
    private String tts;
    private String kns;
    private String wkns;
    private String wtts;
    private Integer score;
    private Integer ckc;
    private Long maxk;//max id of kp
    private Long maxt;//max id of tt

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
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

    public Long getKptBatch() {
        return kptBatch;
    }

    public void setKptBatch(Long kptBatch) {
        this.kptBatch = kptBatch;
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
