package com.bw.edu.ctb.dto;

import java.io.Serializable;

/** subject list */
public class SsDTO implements Serializable {
    private Integer sc;
    private String sn;

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
