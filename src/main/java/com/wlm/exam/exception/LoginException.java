package com.wlm.exam.exception;

public class LoginException extends BaseException{

    public LoginException(Object data) {
        super(ErrorCode.BODY_NOT_MATCH, data);
    }
}
