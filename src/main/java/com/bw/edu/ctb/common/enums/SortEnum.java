package com.bw.edu.ctb.common.enums;

public enum SortEnum {
    ASC("ASC"),
    DESC( "DESC"),

    ;

    private String mode;

    SortEnum(String mode){
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
