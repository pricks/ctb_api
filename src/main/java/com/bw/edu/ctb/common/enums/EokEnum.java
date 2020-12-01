package com.bw.edu.ctb.common.enums;

public enum EokEnum {
    KP_DETAIL(1, "知识点明细"),
    EXERCISE(2, "练习题"),

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

    EokEnum(Integer code, String name){
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
