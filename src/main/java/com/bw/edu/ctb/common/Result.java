package com.bw.edu.ctb.common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
