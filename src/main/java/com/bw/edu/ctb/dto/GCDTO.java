package com.bw.edu.ctb.dto;

import java.io.Serializable;

/** grade & class */
public class GCDTO implements Serializable {
    private Integer dg;
    private Integer gd;//grade code
    private String gdn;//grade name
    private Long subj;//class code

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

    public String getGdn() {
        return gdn;
    }

    public void setGdn(String gdn) {
        this.gdn = gdn;
    }

    public Long getSubj() {
        return subj;
    }

    public void setSubj(Long subj) {
        this.subj = subj;
    }
}
