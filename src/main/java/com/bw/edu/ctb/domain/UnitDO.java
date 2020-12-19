package com.bw.edu.ctb.domain;

import com.bw.edu.ctb.common.qo.UnitQO;

public class UnitDO {
    private Integer dg;
    private Integer gd;
    public Integer cl;
    public Long code;
    public String name;

    public static UnitDO build(UnitQO uq){
        UnitDO unitDO = new UnitDO();
        unitDO.setDg(uq.getDg());
        unitDO.setGd(uq.getGd());
        unitDO.setCl(uq.getCl());
        unitDO.setCode(uq.getCode());
        return unitDO;
    }

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

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
