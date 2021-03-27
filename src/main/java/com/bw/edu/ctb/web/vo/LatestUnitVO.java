package com.bw.edu.ctb.web.vo;

import com.bw.edu.ctb.dao.entity.UnitEntity;

import java.io.Serializable;

/**
 * 最后一次练习的单元信息
 */
public class LatestUnitVO implements Serializable {
    private Long un;//un code
    private String uname;
    private Integer rd;//round
    private Integer ec;//exercise count

    public static LatestUnitVO build(UnitEntity ue){
        LatestUnitVO luv = new LatestUnitVO();
        luv.setUn(ue.getCode());
        luv.setUname(ue.getName());
        luv.setRd(1);
        luv.setEc(0);
        return luv;
    }

    public Long getUn() {
        return un;
    }

    public void setUn(Long un) {
        this.un = un;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        this.rd = rd;
    }

    public Integer getEc() {
        return ec;
    }

    public void setEc(Integer ec) {
        this.ec = ec;
    }
}
