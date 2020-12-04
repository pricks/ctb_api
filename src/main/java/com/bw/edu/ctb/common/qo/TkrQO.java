package com.bw.edu.ctb.common.qo;

import java.io.Serializable;

public class TkrQO extends Paging implements Serializable {
    private Long kpid;
    private Long tid;
    private Integer eok;

    public Long getKpid() {
        return kpid;
    }

    public void setKpid(Long kpid) {
        this.kpid = kpid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Integer getEok() {
        return eok;
    }

    public void setEok(Integer eok) {
        this.eok = eok;
    }
}
