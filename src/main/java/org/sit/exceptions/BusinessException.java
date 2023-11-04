package org.sit.exceptions;

import org.sit.enums.ErrorCode;

public class BusinessException extends Exception{
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static BusinessException error(String message){
        return new BusinessException(ErrorCode.ERROR.getCode(), message);
    }

    public static BusinessException error(ErrorCode errorCode){
        return new BusinessException(errorCode.getCode(), errorCode.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
