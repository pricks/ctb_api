package com.bw.edu.ctb.common;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;
    private Boolean success;

    public Result(){}

    public static Result success(){
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result failure(Integer code, String message){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
