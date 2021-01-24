package com.bw.edu.ctb.common.qo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class TTBactchQO implements Serializable {
    private String atk;
    private Long uid;
    private Long un;
    private Integer dl;
    private Integer eok;
    private Long maxKpId;
    private Long maxTid;
    private String mkp;
    private String mt;
    private Integer rd;
    private Integer rl;//reload，是否开启新一轮测试，此时将从第一个节点重新搜索tt

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
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

    public String getMkp() {
        return mkp;
    }

    public void setMkp(String mkp) {
        this.mkp = mkp;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    public Integer getRl() {
        return rl;
    }

    public void setRl(Integer rl) {
        this.rl = rl;
    }
}
