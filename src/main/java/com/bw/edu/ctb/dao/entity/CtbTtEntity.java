package com.bw.edu.ctb.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class CtbTtEntity {
    private Long id;
    private Long uid;
    private Long un;
    private Date gc;
    private Date gm;
    private Long tid;
    private Integer src;
    private Integer errc;

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

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getErrc() {
        return errc;
    }

    public void setErrc(Integer errc) {
        this.errc = errc;
    }
}
