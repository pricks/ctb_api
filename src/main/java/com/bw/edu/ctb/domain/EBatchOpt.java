package com.bw.edu.ctb.domain;

import java.io.Serializable;

/**
 * 答案选项。例如： A: 李白
 */
public class EBatchOpt implements Serializable {
    private String idx;
    private String cont;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }
}
