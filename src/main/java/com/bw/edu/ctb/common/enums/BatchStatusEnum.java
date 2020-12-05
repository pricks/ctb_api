package com.bw.edu.ctb.common.enums;

public enum BatchStatusEnum {
    GENERATED(1, "已生成"),
    DONE(2, "已完成"),

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

    BatchStatusEnum(Integer code, String name){
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
