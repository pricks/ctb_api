package com.bw.edu.ctb.common.qo;

import java.util.Date;

public class CtbTtQO extends Paging{
    private Long uid;
    private Long id;
    private Long un;
    private String maxGm;//用于分页查询加速
    private String atk;
    private String gc;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public String getMaxGm() {
        return maxGm;
    }

    public void setMaxGm(String maxGm) {
        this.maxGm = maxGm;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }
}
