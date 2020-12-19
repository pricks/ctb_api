package com.bw.edu.ctb.domain;

import java.io.Serializable;

public class SttDlDO implements Serializable {
    private Integer dl;
    private String gc;//最早练习时间
    private Integer ec;//exercise count，练习次数
    private Boolean over;//是否结束全部练习
    private String dlname;
    private Boolean active;
    private Long maxKpId;
    private Long maxTid;
    private Integer tkp;//total kp
    private Integer rkp;//reviewd kp
    private Integer ekp;//wrong kp
    private Integer ascore;//total score，每一次提交都累加
    private Integer lscore;//the latest score, ascore/ec
    private Integer dr;//done round

    public static SttDlDO newInstance(){
        return new SttDlDO();
    }
    public SttDlDO dl(Integer dl){
        this.dl = dl;
        return this;
    }
    public SttDlDO dlname(String dlname){
        this.dlname = dlname;
        return this;
    }
    public SttDlDO active(Boolean active){
        this.active = active;
        return this;
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

    public SttDlDO tkp(Integer tkp){
        this.tkp = tkp;
        return this;
    }
    public SttDlDO rkp(Integer rkp){
        this.rkp = rkp;
        return this;
    }
    public SttDlDO ekp(Integer ekp){
        this.ekp = ekp;
        return this;
    }
    public SttDlDO ascore(Integer ascore){
        this.ascore = ascore;
        return this;
    }
    public SttDlDO dr(Integer dr){
        this.dr = dr;
        return this;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public String getDlname() {
        return dlname;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getTkp() {
        return tkp;
    }

    public void setTkp(Integer tkp) {
        this.tkp = tkp;
    }

    public Integer getRkp() {
        return rkp;
    }

    public void setRkp(Integer rkp) {
        this.rkp = rkp;
    }

    public Integer getEkp() {
        return ekp;
    }

    public void setEkp(Integer ekp) {
        this.ekp = ekp;
    }

    public Integer getAscore() {
        return ascore;
    }

    public void setAscore(Integer ascore) {
        this.ascore = ascore;
    }

    public Integer getLscore() {
        return lscore;
    }

    public void setLscore(Integer lscore) {
        this.lscore = lscore;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Integer getEc() {
        return ec;
    }

    public void setEc(Integer ec) {
        this.ec = ec;
    }

    public Boolean getOver() {
        return over;
    }

    public void setOver(Boolean over) {
        this.over = over;
    }
}
