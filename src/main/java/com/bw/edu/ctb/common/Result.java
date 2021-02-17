package com.bw.edu.ctb.common;

import com.bw.edu.ctb.exception.CtbException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> implements Serializable {
    private String code;
    private String message;
    private T data;
    private Boolean success;
    private Map<String, String> attrs;//正常业务数据通过data返回，因此这里仅支持临时的简单字段

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
    public Result putAttr(String k, String v){
        if(null==attrs){
            attrs = new HashMap<>();
        }
        attrs.put(k, v);
        return this;
    }
    public String getAttr(String k){
        if(null==attrs){
            return null;
        }
        return attrs.get(k);
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
        result.setCode("SYSTEM ERROR");
        result.setMessage("SYSTEM ERROR");
        return result;
    }

    public static Result failure(CtbException e){
        return failure(e.getCode(), e.getDesc());
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

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }
}
