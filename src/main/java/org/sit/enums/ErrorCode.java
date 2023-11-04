package org.sit.enums;

public enum ErrorCode {
    SUCCESS(200, "�ɹ�"),
    FAIL(400, "����ʧ��"),
    ERROR(9999, "ϵͳ����"),
    NETWORK_ERROR(401, "�����������ϵ����Ա��Ⱥ��"),
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