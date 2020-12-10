package com.bw.edu.ctb.common.enums;

/** 用户类型：本地或三方 */
public enum AuthTypeEnum {
    LOCAL(1, "本地"),
    THIRD(2, "三方"),

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

    AuthTypeEnum(Integer code, String name){
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
