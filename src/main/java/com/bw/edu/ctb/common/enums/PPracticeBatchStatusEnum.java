package com.bw.edu.ctb.common.enums;

public enum PPracticeBatchStatusEnum {

    NEW(0, "刚创建"),
    PRACTICING(1, "练习中"),
    COMMITED(2, "已提交"),
    ANALIZED(3, "分析中"),
    DONE(4, "完成"),


    ;


    private int status;
    private String desc;

    PPracticeBatchStatusEnum(int status, String desc){
        this.status = status;
        this.desc = desc;
    }
}
