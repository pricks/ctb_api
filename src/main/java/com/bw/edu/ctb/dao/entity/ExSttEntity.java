package com.bw.edu.ctb.dao.entity;

import java.util.Date;

/**
 */
public class ExSttEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Long un;
    private Integer rd;
    private Integer dl;
    private Integer tpg;
    private String tKps;
    private String mwKps;
    private Integer aSc;//average score
    private Integer lSc;
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

    public Integer getTpg() {
        return tpg;
    }

    public void setTpg(Integer tpg) {
        this.tpg = tpg;
    }

    public String gettKps() {
        return tKps;
    }

    public void settKps(String tKps) {
        this.tKps = tKps;
    }

    public String getMwKps() {
        return mwKps;
    }

    public void setMwKps(String mwKps) {
        this.mwKps = mwKps;
    }

    public Integer getaSc() {
        return aSc;
    }

    public void setaSc(Integer aSc) {
        this.aSc = aSc;
    }

    public Integer getlSc() {
        return lSc;
    }

    public void setlSc(Integer lSc) {
        this.lSc = lSc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
