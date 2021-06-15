package com.bw.edu.ctb.common.enums.subjects;

/**
 * 见《编码规则》
 */
public enum DagangEnum {
    RENJIAOBAN(90, "人教版"),
    SUJIAOBAN(91, "SUJIAOBAN"),

    ;

    public String getName(Integer code){
        for(DagangEnum e : DagangEnum.values()){
            if(e.getCode().equals(code)){
                return e.getName();
            }
        }
        return null;
    }

    private Integer code;
    private String name;

    DagangEnum(Integer code, String name){
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
