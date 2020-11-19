package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class UnitQO extends Paging implements Serializable {
    private Long code;
    private Integer gd;
    private Integer cl;
    private Integer status;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
