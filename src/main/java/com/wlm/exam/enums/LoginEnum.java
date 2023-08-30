package com.wlm.exam.enums;

import lombok.Getter;

@Getter
public enum LoginEnum {
    SUCCESS(0, "成功"),
    INVALID_PARAM(1, "参数不合法"),
    LOGINUSER_DISABLE(2, "用户不存在"),
    LOGINPASSWORD_WRONG(3, "密码错误"),
    LOGINTYPE_DISABLE(4, "用户类型错误")
    ;
    private Integer code;
    private String message;

    LoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
