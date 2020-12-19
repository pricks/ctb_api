package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class ExSttByclQO extends Paging implements Serializable {
    private Integer dg;
    private Integer gd;
    private Integer cl;
    private Long uid;

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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
