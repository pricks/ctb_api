package com.bw.edu.ctb.web.vo;

import java.io.Serializable;

public class GradeVO implements Serializable {
    private Integer v;
    private String name;

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
