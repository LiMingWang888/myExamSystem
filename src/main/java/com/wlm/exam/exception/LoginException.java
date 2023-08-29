package com.wlm.exam.exception;

public class LoginException extends BaseException{

    public LoginException(Object data) {
        super(ErrorCode.RESOURCE_NOT_FOUND, data);
    }
}
