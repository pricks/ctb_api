package com.bw.edu.ctb.common.qo.usr;

import com.bw.edu.ctb.common.qo.Paging;

public class TUsrQO extends Paging{
    private Long uid;
    private String oid;
    private Integer type;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
