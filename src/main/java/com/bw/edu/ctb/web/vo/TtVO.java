package com.bw.edu.ctb.web.vo;

import java.io.Serializable;
import java.util.List;

public class TtVO implements Serializable {
    private Integer dg;
    private Integer gd;
    private Long cl;//subject code
    private Long un;//unit code
    private Integer dl;
    private List<Long> kp;//前端页面传过来是个arrya，叶子节点是list的最后一个元素
    private Integer type;
    private String content;
    private Integer ops;
    private String aA;
    private String aB;
    private String aC;
    private String aD;

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

    public Long getCl() {
        return cl;
    }

    public void setCl(Long cl) {
        this.cl = cl;
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

    public List<Long> getKp() {
        return kp;
    }

    public void setKp(List<Long> kp) {
        this.kp = kp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOps() {
        return ops;
    }

    public void setOps(Integer ops) {
        this.ops = ops;
    }

    public String getaA() {
        return aA;
    }

    public void setaA(String aA) {
        this.aA = aA;
    }

    public String getaB() {
        return aB;
    }

    public void setaB(String aB) {
        this.aB = aB;
    }

    public String getaC() {
        return aC;
    }

    public void setaC(String aC) {
        this.aC = aC;
    }

    public String getaD() {
        return aD;
    }

    public void setaD(String aD) {
        this.aD = aD;
    }
}
