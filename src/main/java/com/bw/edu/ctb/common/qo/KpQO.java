package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class KpQO extends Paging implements Serializable {
    private Long id;
    private Long un;
    private Long dl;
    private Long pid;
    private Integer korder;
    private Integer level;
    private Integer status;

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

    public Long getDl() {
        return dl;
    }

    public void setDl(Long dl) {
        this.dl = dl;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
