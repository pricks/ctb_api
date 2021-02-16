package com.bw.edu.ctb.web.vo;

import java.io.Serializable;

/**
 * local login vo
 */
public class LLVO implements Serializable {
    private String phone;
    private String pwd;
    private String ats;//attributes

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAts() {
        return ats;
    }

    public void setAts(String ats) {
        this.ats = ats;
    }
}
