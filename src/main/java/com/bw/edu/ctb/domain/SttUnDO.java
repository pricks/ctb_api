package com.bw.edu.ctb.domain;

import java.io.Serializable;
import java.util.List;

public class SttUnDO implements Serializable {
    private Long un;
    private String uname;
    private Integer rd;//round
    private List<SttDlDO> dls;

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    public List<SttDlDO> getDls() {
        return dls;
    }

    public void setDls(List<SttDlDO> dls) {
        this.dls = dls;
    }
}
