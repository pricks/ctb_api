package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class ExRecQO extends Paging implements Serializable {
    private Long uid;
    private Integer cl;
    private Long un;

    public ExRecQO(Long uid, Integer cl){
        this.uid = uid;
        this.cl = cl;
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

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }
}
