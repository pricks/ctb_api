package com.bw.edu.ctb.domain;

import java.io.Serializable;
import java.util.List;

public class EBatch implements Serializable {
    private Long eb_id;
    private Integer tt_count;
    private Integer tt_g_merge_count;
    private Integer kp_dl;
    private List<EBatchTT> tt_list;

    public Long getEb_id() {
        return eb_id;
    }

    public void setEb_id(Long eb_id) {
        this.eb_id = eb_id;
    }

    public Integer getTt_count() {
        return tt_count;
    }

    public void setTt_count(Integer tt_count) {
        this.tt_count = tt_count;
    }

    public Integer getTt_g_merge_count() {
        return tt_g_merge_count;
    }

    public void setTt_g_merge_count(Integer tt_g_merge_count) {
        this.tt_g_merge_count = tt_g_merge_count;
    }

    public Integer getKp_dl() {
        return kp_dl;
    }

    public void setKp_dl(Integer kp_dl) {
        this.kp_dl = kp_dl;
    }

    public List<EBatchTT> getTt_list() {
        return tt_list;
    }

    public void setTt_list(List<EBatchTT> tt_list) {
        this.tt_list = tt_list;
    }
}
