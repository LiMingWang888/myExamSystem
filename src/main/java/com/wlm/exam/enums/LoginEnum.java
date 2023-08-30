package com.wlm.exam.enums;

import lombok.Getter;

@Getter
public enum LoginEnum {
    /**
     * 登录信息枚举
     */
    SUCCESS(0, "成功"),
    INVALID_PARAM(1, "参数不合法"),
    LOGINUSER_DISABLE(2, "用户不存在"),
    LOGINPASSWORD_WRONG(3, "登录密码错误"),
    LOGINTYPE_DISABLE(4, "用户类型错误")
    ;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    LoginEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
