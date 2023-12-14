package org.sit.vo;

import lombok.*;
import org.sit.enums.ErrorCode;

/**
 * @author WangZhen
 * @Date 2023/11/6 20:05
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ResponseData<T> {
    private Integer code;
    private String message;
    private T data;

    private ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    private ResponseData(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    private ResponseData(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = data;
    }


    public static ResponseData success(){
        return new ResponseData(ErrorCode.SUCCESS);
    }
    public static ResponseData success(String message){
        return new ResponseData(ErrorCode.SUCCESS.getCode(), message);
    }
    public static <T> ResponseData success(T data){
        return new ResponseData(ErrorCode.SUCCESS, data);
    }
    public static ResponseData failure(){
        return new ResponseData(ErrorCode.FAIL);
    }
    public static ResponseData failure(String message){
        return new ResponseData(ErrorCode.FAIL.getCode(), message);
    }
    public static <T> ResponseData failure(T data){
        return new ResponseData(ErrorCode.FAIL, data);
    }

}
