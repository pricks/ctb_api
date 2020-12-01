package com.bw.edu.ctb.common.enums;

public enum StatusEnum {
    CREATED(1, "创建"),
    WAIT_AUTH(2, "待审核"),
    AUTHED(3, "已审核"),
    PULISHED(4, "已发布"),
    DELETED(5, "删除"),

    ;

    public String getName(Integer code){
        for(DlEnum e : DlEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    StatusEnum(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
