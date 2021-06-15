package com.bw.edu.ctb.common.enums.subjects;

public enum SubjectEnum {
    YUWEN(1, "语文"),
    SHUXUE(2, "数学"),
    KOUSUAN(3, "口算"),
    GUWEN(4, "古文"),
    YINGYU(5, "英语"),
    KEXUE(6, "科学"),
    XIGUAN(7, "习惯"),
    MEISHU(8, "初1"),

    /** 语文系列阅读类 */
    YD_SHIJI1(31, "史记1"),
    YD_SHIJI2(32, "史记2"),

    ;

    public String getName(Integer code){
        for(SubjectEnum e : SubjectEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    SubjectEnum(Integer code, String name){
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
