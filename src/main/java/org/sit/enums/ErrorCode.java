package org.sit.enums;
/**
 * 全局错误信息枚举类
 * @author WangZhen
 * @date 2023/11/4 8:15
 */
public enum ErrorCode {
    SUCCESS(200, "成功"),
    FAIL(400, "请求失败"),
    ERROR(9999, "系统错误"),
    NETWORK_ERROR(401, "网络错误，请联系管理员入群。"),
    ;

    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
