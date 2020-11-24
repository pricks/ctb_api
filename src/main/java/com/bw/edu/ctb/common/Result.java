package com.bw.edu.ctb.common;

import com.bw.edu.ctb.exception.CtbException;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private String code;
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

    public static Result failure(String code, String message){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result failure(){
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("SYSTEM ERROR");
        return result;
    }

    public static Result failure(CtbException e){
        return failure(e.getCode(), e.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
