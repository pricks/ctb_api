package com.bw.edu.ctb.domain;

import com.bw.edu.ctb.common.util.JacksonUtil;
import com.bw.edu.ctb.common.util.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EBatchTT implements Serializable {
    private String gc;//创建时间
    private Boolean tt_g;
    private Integer t_idx;
    private Long tid;
    private String tt_ct;
    private Boolean tt_offline;
    private Integer tt_type;
    private List<EBatchOpt> t_ops;
    private String tt_answer;
    private String sel;
    private Integer correct;
    private Long r_kp_id;
    private Integer dl;
    private String tat;//text analysis

    private Integer t_g_count;
    private String tt_g_rpm;
    private List<EBatchTT> t_g_list;

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public Boolean getTt_g() {
        return tt_g;
    }

    public void setTt_g(Boolean tt_g) {
        this.tt_g = tt_g;
    }

    public Integer getT_idx() {
        return t_idx;
    }

    public void setT_idx(Integer t_idx) {
        this.t_idx = t_idx;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTt_ct() {
        return tt_ct;
    }

    public void setTt_ct(String tt_ct) {
        this.tt_ct = tt_ct;
    }

    public Boolean getTt_offline() {
        return tt_offline;
    }

    public void setTt_offline(Boolean tt_offline) {
        this.tt_offline = tt_offline;
    }

    public Integer getTt_type() {
        return tt_type;
    }

    public void setTt_type(Integer tt_type) {
        this.tt_type = tt_type;
    }

    public List<EBatchOpt> getT_ops() {
        return t_ops;
    }

    public void setT_ops(List<EBatchOpt> t_ops) {
        this.t_ops = t_ops;
    }

    public void setT_ops(String ots) {
        if(StringUtil.isEmpty(ots)){
            return;
        }
        try {
            this.t_ops = (List<EBatchOpt>) JacksonUtil.deserialize(ots, new TypeReference<List<EBatchOpt>>(){/**/});
        }catch (Exception e){

        }
    }

    public String getTt_answer() {
        return tt_answer;
    }

    public void setTt_answer(String tt_answer) {
        this.tt_answer = tt_answer;
    }

    public String getSel() {
        return sel;
    }

    public void setSel(String sel) {
        this.sel = sel;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Long getR_kp_id() {
        return r_kp_id;
    }

    public void setR_kp_id(Long r_kp_id) {
        this.r_kp_id = r_kp_id;
    }

    public Integer getT_g_count() {
        return t_g_count;
    }

    public void setT_g_count(Integer t_g_count) {
        this.t_g_count = t_g_count;
    }

    public String getTt_g_rpm() {
        return tt_g_rpm;
    }

    public void setTt_g_rpm(String tt_g_rpm) {
        this.tt_g_rpm = tt_g_rpm;
    }

    public List<EBatchTT> getT_g_list() {
        return t_g_list;
    }

    public void setT_g_list(List<EBatchTT> t_g_list) {
        this.t_g_list = t_g_list;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
    }
}
