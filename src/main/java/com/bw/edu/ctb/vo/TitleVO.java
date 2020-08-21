package com.bw.edu.ctb.vo;

import java.io.Serializable;

/**
 * timu view object
 */
public class TitleVO implements Serializable {
    private Integer className;
    private Integer grade;
    private String content;
    private Integer type;
    private String answer;
    private Integer region;
    private Integer dagang;

    @Override
    public String toString(){
        return String.format("class=%s,grade=%s,type=%s,region=%s", className,grade,type,region);
    }

    public Integer getClassName() {
        return className;
    }

    public void setClassName(Integer className) {
        this.className = className;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getDagang() {
        return dagang;
    }

    public void setDagang(Integer dagang) {
        this.dagang = dagang;
    }
}
