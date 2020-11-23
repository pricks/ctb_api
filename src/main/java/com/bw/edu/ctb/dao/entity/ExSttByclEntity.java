package com.bw.edu.ctb.dao.entity;

import java.util.Date;

public class ExSttByclEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long uid;
    private Integer cl;
    private String stt;

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

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }
}
