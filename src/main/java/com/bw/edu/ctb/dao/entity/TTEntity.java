package com.bw.edu.ctb.dao.entity;

import com.bw.edu.ctb.util.MD5Utils;
import java.util.Date;

public class TTEntity {
    private Long id;
    private Date gc;
    private Date gm;
    private Long un;
    private Integer dl;//dificault level
    private Integer eok;
    private String tc;//content
    private String tcEnc;//encription
    private Integer ts;//status
    private Integer tType;
    private Integer oi;//offline
    private Long tgi;//group id
    private String ots;//optional answers
    private String tat;//analysis_text
    private String tav;//analysis_video
    private String tca;//correct answer
    private Long aid;//author

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGc() {
        return gc;
    }

    public void setGc(Date gc) {
        this.gc = gc;
    }

    public Date getGm() {
        return gm;
    }

    public void setGm(Date gm) {
        this.gm = gm;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public Integer getDl() {
        return dl;
    }

    public void setDl(Integer dl) {
        this.dl = dl;
    }

    public Integer getEok() {
        return eok;
    }

    public void setEok(Integer eok) {
        this.eok = eok;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
        this.setTcEnc(MD5Utils.stringToMD5(tc));
    }

    public void setTcEnc(String tcEnc) {
        this.tcEnc = tcEnc;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    public Integer getOi() {
        return oi;
    }

    public void setOi(Integer oi) {
        this.oi = oi;
    }

    public Long getTgi() {
        return tgi;
    }

    public void setTgi(Long tgi) {
        this.tgi = tgi;
    }

    public String getOts() {
        return ots;
    }

    public void setOts(String ots) {
        this.ots = ots;
    }

    public String getTat() {
        return tat;
    }

    public void setTat(String tat) {
        this.tat = tat;
    }

    public String getTav() {
        return tav;
    }

    public void setTav(String tav) {
        this.tav = tav;
    }

    public String getTca() {
        return tca;
    }

    public void setTca(String tca) {
        this.tca = tca;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }
}
