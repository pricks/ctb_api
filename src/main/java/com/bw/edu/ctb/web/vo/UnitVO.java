package com.bw.edu.ctb.web.vo;

import java.io.Serializable;

public class UnitVO implements Serializable {
    private Long uc;//unit code
    private String un;//unit name

    public Long getUc() {
        return uc;
    }

    public void setUc(Long uc) {
        this.uc = uc;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }
}
