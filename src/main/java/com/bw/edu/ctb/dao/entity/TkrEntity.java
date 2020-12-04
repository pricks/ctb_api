package com.bw.edu.ctb.dao.entity;

import java.util.Date;

public class TkrEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Integer eok;
    private Long kpid;
    private Long tid;

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

    public Integer getEok() {
        return eok;
    }

    public void setEok(Integer eok) {
        this.eok = eok;
    }

    public Long getKpid() {
        return kpid;
    }

    public void setKpid(Long kpid) {
        this.kpid = kpid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
