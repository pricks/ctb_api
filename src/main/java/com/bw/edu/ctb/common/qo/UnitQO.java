package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class UnitQO extends Paging implements Serializable {
    private Long code;
    private Integer dg;
    private Integer gd;
    private Integer cl;
    private Long un;
    private String atk;
    private Integer status;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getDg() {
        return dg;
    }

    public void setDg(Integer dg) {
        this.dg = dg;
    }

    public Integer getGd() {
        return gd;
    }

    public void setGd(Integer gd) {
        this.gd = gd;
    }

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }
}
