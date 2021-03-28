package com.bw.edu.ctb.common.enums.subjects;

import com.bw.edu.ctb.common.enums.DlEnum;

public enum GradeEnum {
    X11(11, "1年级上"),
    X12(12, "1年级下"),
    X21(21, "2年级上"),
    X22(22, "2年级下"),
    X3(3, "3年级"),
    X4(4, "4年级"),
    X5(5, "5年级"),
    X6(6, "6年级"),

    C1(11, "初1"),
    C2(12, "初2"),
    C3(13, "初3"),

    G1(21, "高1"),
    G2(22, "高2"),
    G3(23, "高3"),

    D1(31, "大1"),
    D2(32, "大2"),
    D3(33, "大3"),
    D4(34, "大4"),

    ;

    public static String getName(Integer code){
        for(GradeEnum e : GradeEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    GradeEnum(Integer code, String name){
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
