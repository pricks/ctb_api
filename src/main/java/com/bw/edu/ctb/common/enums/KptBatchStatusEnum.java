package com.bw.edu.ctb.common.enums;

public enum KptBatchStatusEnum {
    CREATED(1, "创建"),
    COMMITED(2, "提交"),
    GEN_BATCH(3, "生成批次"),

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

    KptBatchStatusEnum(Integer code, String name){
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
