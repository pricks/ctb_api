package com.bw.edu.ctb.exception;

public enum CtbExceptionEnum {
    /** ======================题目管理模块错误码：1001开头============================ */
    CLASS_IS_NULL(11001001, "CLASS_IS_NULL", "课程为空"),
    CONTENT_IS_NULL(11001002, "CONTENT_IS_NULL", "题目内容为空"),
    ANSWER_IS_NULL(11001003, "ANSWER_IS_NULL", "题目答案为空"),
    GRADE_IS_NULL(11001004, "GRADE_IS_NULL", "年级为空"),
    TYPE_IS_NULL(11001005, "TYPE_IS_NULL", "难度等级为空"),
    REGION_IS_NULL(11001006, "REGION_IS_NULL", "区域为空"),
    DAGANG_IS_NULL(11001007, "DAGANG_IS_NULL", "大纲为空"),

    MULTI_MIN_ORDER_QUOTA(11001031, "MULTI_MIN_ORDER_QUOTA", "出现多个顺序相同的、且为最小顺序的配额记录"),
    QUOTA_IS_NULL_WHEN_QUERY_BY_MIN_QUOTA(11001032, "QUOTA_IS_NULL_WHEN_QUERY_BY_MIN_QUOTA", "根据最小配额查询相同服务下的配额时为空"),
    MIN_QUOTA_NOT_SELF(11001033, "MIN_QUOTA_NOT_SELF", "根据最小配额查询相同服务下的配额时，仅查询到1条，但是计量要素不是同一个"),
    QUOTA_REMAINING_IS_NULL(11001034, "QUOTA_REMAINING_IS_NULL", "配额余量为空"),


    /** ======================CECE错误码：1003开头============================ */
    UNIT_IS_NULL(11003001, "UNIT_IS_NULL", "单元为空"),
    UNIT_TOO_MANY(11003002, "UNIT_TOO_MANY", "单元太多"),
    EX_STT_BYCL_TOO_MANY(11003003, "EX_STT_BYCL_TOO_MANY", "单元统计太多"),


    /** ======================各模块通用错误码：999开头============================ */
    OBJECT_NULL(1999001, "OBJECT_IS_NULL", "对象为空"),
    Collection_SORT_ERROR(1999002, "Collection_SORT_ERROR", "排序异常"),
    DB_DUPLICATE_ENTRY(1999003, "DB_DUPLICATE_ENTRY", "DB唯一键冲突"),

    ;

    public static void promoteException(CtbExceptionEnum ctbExceptionEnum, Object o){
        if(null == o){
            promoteException(ctbExceptionEnum);
        }
        String errorMsg = String.format("%s. bizMsg=%s", ctbExceptionEnum.getDesc(), o.toString());
        throw new CtbException(ctbExceptionEnum.getCode(), errorMsg);
    }
    public static void promoteException(CtbExceptionEnum ctbExceptionEnum, String bizMsg){
        String errorMsg = String.format("%s. bizMsg=%s", ctbExceptionEnum.getDesc(), bizMsg);
        throw new CtbException(ctbExceptionEnum.getCode(), errorMsg);
    }
    public static void promoteException(CtbExceptionEnum ctbExceptionEnum){
        throw new CtbException(ctbExceptionEnum.getCode(), ctbExceptionEnum.getDesc());
    }

    private int num;
    private String code;
    private String desc;

    CtbExceptionEnum(int num, String code, String desc){
        this.num = num;
        this.code = code;
        this.desc = desc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
