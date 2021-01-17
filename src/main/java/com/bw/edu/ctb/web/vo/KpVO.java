package com.bw.edu.ctb.web.vo;

import java.io.Serializable;
import java.util.List;

public class KpVO implements Serializable {
    private Long value;
    private String label;//point
    private Long p;//parent
    private Integer level;
    private Integer korder;//同一个level下不同
    private List<KpVO> children;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getP() {
        return p;
    }

    public void setP(Long p) {
        this.p = p;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getKorder() {
        return korder;
    }

    public void setKorder(Integer korder) {
        this.korder = korder;
    }

    public List<KpVO> getChildren() {
        return children;
    }

    public void setChildren(List<KpVO> children) {
        this.children = children;
    }
}
