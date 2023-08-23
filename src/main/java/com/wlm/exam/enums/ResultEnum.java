package com.wlm.exam.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    INVALID_PARAM(1,"输入参数错误，请重试"),
    LOGINNAME_DISABLE(2, "用户名不存在，请重试"),
    LOGINPASSWORD_WRONG(3, "密码错误，请重试"),
    LOGINTYPE_DISABLE(4, "用户类型错误，请重试"),
    SUCCESS(5, "成功");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
