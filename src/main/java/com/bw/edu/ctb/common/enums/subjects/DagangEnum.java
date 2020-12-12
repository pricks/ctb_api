package com.bw.edu.ctb.common.enums.subjects;

import com.bw.edu.ctb.common.enums.DlEnum;

public enum DagangEnum {
    RENJIAOBAN(1, "人教版"),
    SUJIAOBAN(2, "SUJIAOBAN"),

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
