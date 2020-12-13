package com.bw.edu.ctb.dao.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/** subj & grade relation */
public class SGEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Integer dg;
    private Integer gd;
    private Integer sc;//subj code
    private String sn;//subj name

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

    public Integer getSc() {
        return sc;
    }

    public void setSc(Integer sc) {
        this.sc = sc;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
