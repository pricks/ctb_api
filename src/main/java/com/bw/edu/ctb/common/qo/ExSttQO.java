package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class ExSttQO extends Paging implements Serializable {
    private Long uid;
    private Long un;
    private Integer status;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
