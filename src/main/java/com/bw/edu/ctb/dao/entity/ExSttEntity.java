package com.bw.edu.ctb.dao.entity;

import lombok.Data;

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
    private Long dl;
    private Integer tpg;
    private String tkps;
    private String mwkps;
    private Integer avsc;//average score
    private Integer lsc;
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

    public Long getDl() {
        return dl;
    }

    public void setDl(Long dl) {
        this.dl = dl;
    }

    public Integer getTpg() {
        return tpg;
    }

    public void setTpg(Integer tpg) {
        this.tpg = tpg;
    }

    public String getTkps() {
        return tkps;
    }

    public void setTkps(String tkps) {
        this.tkps = tkps;
    }

    public String getMwkps() {
        return mwkps;
    }

    public void setMwkps(String mwkps) {
        this.mwkps = mwkps;
    }

    public Integer getAvsc() {
        return avsc;
    }

    public void setAvsc(Integer avsc) {
        this.avsc = avsc;
    }

    public Integer getLsc() {
        return lsc;
    }

    public void setLsc(Integer lsc) {
        this.lsc = lsc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
