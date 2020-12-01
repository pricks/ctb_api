package com.bw.edu.ctb.common.enums;

public enum DlEnum {

    BASIC(1, "基础知识"),
    DIFICAULT(2, "重难点"),
    MOST_WRONG(3, "易错"),
    EXERCISE(4, "测试"),

    ;

    public String getName(Integer code){
        for(DlEnum e : DlEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    public static int size(){
        return DlEnum.values().length;
    }

    private Integer code;
    private String name;

    DlEnum(Integer code, String name){
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
