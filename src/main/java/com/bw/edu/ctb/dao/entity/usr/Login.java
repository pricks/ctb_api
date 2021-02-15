package com.bw.edu.ctb.dao.entity.usr;

import java.util.Date;

public class Login {
    private Long id;
    private Date gc;
    private Long uid;
    private String ip;
    private String mark;//备注信息

    public Login(Long uid, String ip){
        this.uid = uid;
        this.ip = ip;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
