package com.bw.edu.ctb.domain;

import java.io.Serializable;

public class SttDlDO implements Serializable {
    private Integer dl;
    private String dlname;
    private Boolean active;
    private Integer tkp;//total kp
    private Integer rkp;//reviewd kp
    private Integer ekp;//wrong kp
    private Integer ascore;//avg score
    private Integer dr;//done round

    public static SttDlDO newInstance(){
        return new SttDlDO();
    }
    public SttDlDO dl(Integer dl){
        this.dl = dl;
        return this;
    }
    public SttDlDO dlname(String dlname){
        this.dlname = dlname;
        return this;
    }
    public SttDlDO active(Boolean active){
        this.active = active;
        return this;
    }
    public SttDlDO tkp(Integer tkp){
        this.tkp = tkp;
        return this;
    }
    public SttDlDO rkp(Integer rkp){
        this.rkp = rkp;
        return this;
    }
    public SttDlDO ekp(Integer ekp){
        this.ekp = ekp;
        return this;
    }
    public SttDlDO ascore(Integer ascore){
        this.ascore = ascore;
        return this;
    }
    public SttDlDO dr(Integer dr){
        this.dr = dr;
        return this;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public String getDlname() {
        return dlname;
    }

    public void setDlname(String dlname) {
        this.dlname = dlname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getTkp() {
        return tkp;
    }

    public void setTkp(Integer tkp) {
        this.tkp = tkp;
    }

    public Integer getRkp() {
        return rkp;
    }

    public void setRkp(Integer rkp) {
        this.rkp = rkp;
    }

    public Integer getEkp() {
        return ekp;
    }

    public void setEkp(Integer ekp) {
        this.ekp = ekp;
    }

    public Integer getAscore() {
        return ascore;
    }

    public void setAscore(Integer ascore) {
        this.ascore = ascore;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }
}
