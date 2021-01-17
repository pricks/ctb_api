package com.bw.edu.ctb.common.enums.subjects;

public enum TTypeEnum {
    DANXUAN(1, "单选"),
    DUOXUAN(2, "多选"),
    WENDA(3, "问答"),
    TIANKONG(4, "填空"),
    YINGYONG(5, "应用"),
    YUEDULIJIE(6, "阅读理解"),

    ;

    public static String getName(Integer code){
        for(TTypeEnum e : TTypeEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    TTypeEnum(Integer code, String name){
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
