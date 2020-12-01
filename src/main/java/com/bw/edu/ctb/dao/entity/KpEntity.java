package com.bw.edu.ctb.dao.entity;

import com.bw.edu.ctb.util.MD5Utils;
import lombok.Data;

import java.util.Date;

public class KpEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long un;
    private Integer dl;
    private Long pid;
    private String point;
    private String penc;
    private Integer korder;
    private Integer level;
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
        this.setPenc(MD5Utils.stringToMD5(point));
    }

    public void setPenc(String penc) {
        this.penc = penc;
    }

    public Integer getKorder() {
        return korder;
    }

    public void setKorder(Integer korder) {
        this.korder = korder;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
