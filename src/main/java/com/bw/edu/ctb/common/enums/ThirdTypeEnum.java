package com.bw.edu.ctb.common.enums;

/** 三方登录类型 */
public enum ThirdTypeEnum {
    WEIXIN(1, "weixin"),
    ALIPAY(2, "alipay"),
    TOUTIAO(3, "toutiao"),
    QQ(4, "qq"),

    ;

    public static String getName(Integer code){
        for(DlEnum e : DlEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    ThirdTypeEnum(Integer code, String name){
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
