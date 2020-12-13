package com.bw.edu.ctb.web.vo;

import java.io.Serializable;

public class ExRecVO implements Serializable {
    private String atk;
    private Long un;
    private Integer dl;
    private Long kptBatch;
    private String tts;
    private String kns;
    private String wkns;
    private String wtts;
    private Integer score;
    private Integer ckc;

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
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
}
