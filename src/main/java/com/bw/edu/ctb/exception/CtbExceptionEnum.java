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


    /** ======================CECE错误码：1003开头============================ */
    UNIT_IS_NULL(11003001, "UNIT_IS_NULL", "单元为空"),
    UNIT_TOO_MANY(11003002, "UNIT_TOO_MANY", "单元太多"),
    EX_STT_BYCL_TOO_MANY(11003003, "EX_STT_BYCL_TOO_MANY", "单元统计太多"),
    KP_IS_NULL(11003004, "KP_IS_NULL", "知识点分类不存在"),
    DL_IS_NULL(11003005, "DL_IS_NULL", "难度等级为空"),
    EOK_IS_NULL(11003006, "EOK_IS_NULL", "难度等级为空"),
    KPT_BATCH_TIDS_IS_NULL(11003007, "KPT_BATCH_TIDS_IS_NULL", "没有题目"),
    EX_STT_BYCL_IS_NULL(11003008, "EX_STT_BYCL_IS_NULL", "没有ex_stt_bycl"),
    TTID_NULL_FOR_BATCH(11003009, "TTID_NULL_FOR_BATCH", "没有tts，无法创建batch实例"),
    EX_STT_BYCL_CONT_NULL(11003010, "EX_STT_BYCL_CONT_NULL", "ex_stt字段为空"),
    EXREC_NULL(11003011, "EXREC_NULL", "练习记录不存在"),


    /** ======================LOGIN错误码：1004开头============================ */
    USER_INFO_NULL(11004001, "USER_INFO_NULL", "用户信息错误"),
    USER_TYPE_NULL(11004002, "USER_TYPE_NULL", "用户信息错误"),
    MULTIPLE_SAME_TUSR(11004003, "MULTIPLE_SAME_TUSR", "用户重复"),
    UN_SUPPORTED_LOGIN_TYPE(11004004, "UN_SUPPORTED_LOGIN_TYPE", "不支持的登录方式"),


    /** ======================各模块通用错误码：999开头============================ */
    OBJECT_NULL(1999001, "OBJECT_IS_NULL", "对象为空"),
    Collection_SORT_ERROR(1999002, "Collection_SORT_ERROR", "排序异常"),
    DB_DUPLICATE_ENTRY(1999003, "DB_DUPLICATE_ENTRY", "DB唯一键冲突"),
    PARAM_NULL(1999004, "PARAM_NULL", "参数为空"),
    DB_WRITE_ERROR(1999005, "DB_WRITE_ERROR", "DB写异常"),
    PARAM_NOT_RULED(1999006, "PARAM_NOT_RULED", "非法参数"),
    PAC_INTERRUPTED(1999007, "PAC_INTERRUPTED", "线程中断异常"),
    DESERIALIZE_FAILED(1999008, "DESERIALIZE_FAILED", "反序列化失败"),

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
    public static void promoteException(String code, String msg){
        throw new CtbException(code, msg);
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
