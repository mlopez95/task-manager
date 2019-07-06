package com.sodep.api.exception;

/**
 * @author mlopez
 * @fecha 2019-05-11,12:19
 */


public class ApiException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;

    public ApiException(String message){
        super(message);
    }
    public ApiException(String message, String code){
        super(message);
        this.code = code;
    }

    public ApiException(String message, Throwable cause){
        super(message, cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
