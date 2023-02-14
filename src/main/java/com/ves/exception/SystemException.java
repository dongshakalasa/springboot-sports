package com.ves.exception;

public class SystemException extends RuntimeException{
    private Integer code;


    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}