package com.bw.edu.ctb.dto;

import java.io.Serializable;

public class UsrDTO implements Serializable {
    private Long id;//uid
    private String atk;//token

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtk() {
        return atk;
    }

    public void setAtk(String atk) {
        this.atk = atk;
    }
}
