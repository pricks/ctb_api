package com.bw.edu.ctb.common.qo.usr;

import com.bw.edu.ctb.common.qo.Paging;

import java.io.Serializable;

public class TUsrQO extends Paging{
    private Long uid;
    private String nick;
    private Integer type;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
