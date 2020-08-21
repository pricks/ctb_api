package com.bw.edu.ctb.exception;

public class CtbException extends RuntimeException{
    private String code;
    private String desc;

    /**
     * Creates an SmsException.
     *
     * @param msg The error message
     */
    public CtbException(String msg)
    {
        super(msg);
    }

    public CtbException(String code, String msg){
        super(String.format("code=%s, error=%s", code, msg));
        this.code = code;
        this.desc = msg;
    }

    /**
     * Creates an SmsException.
     *
     * @param msg The error message
     * @param cause Chained exception
     */
    public CtbException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    /**
     * Creates an SmsException.
     *
     * @param cause Chained exception
     */
    public CtbException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public String toString(){
        return String.format("code=%s, error=%s", code, desc);
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
