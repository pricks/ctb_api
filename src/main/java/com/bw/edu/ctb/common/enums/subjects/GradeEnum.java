package com.bw.edu.ctb.common.enums.subjects;

/**
 * 具体规则见《UN编码规则》
 */
public enum GradeEnum {
    X11(211, "1年级上"),
    X12(212, "1年级下"),
    X21(221, "2年级上"),
    X22(222, "2年级下"),
    X31(231, "3年级上"),
    X32(232, "3年级下"),
    X41(241, "4年级上"),
    X42(242, "4年级下"),
    X51(251, "5年级上"),
    X52(252, "5年级下"),
    X61(261, "6年级上"),
    X62(262, "6年级下"),


    //todo 下面的年级要区分上下学期，并参考《UN编码规则》重新编码
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
