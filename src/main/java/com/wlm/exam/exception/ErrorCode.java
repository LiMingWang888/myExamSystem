package com.wlm.exam.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    SUCCESS(1000, HttpStatus.OK, "成功"),
    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到该资源"),
    FILE_EXPIRED(1002, HttpStatus.NOT_FOUND, "资源已过期"),
    PRIVILEGE_ERROR(1003, HttpStatus.BAD_GATEWAY, "权限错误，请联系管理员"),
    BODY_NOT_MATCH(1004, HttpStatus.BAD_REQUEST, "请求数据格式验证失败"),

    INTERNAL_SERVER_ERROR(1005, HttpStatus.NO_CONTENT, "服务器内部错误"),
    ;
    private final int code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
