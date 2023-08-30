package com.wlm.exam.exception;

/**
 * @author wlm
 * @date 2023/8/30 - 10:32
 */
public class BusiException extends BaseException {
    public BusiException(String message) {
        super(ErrorCode.PRIVILEGE_ERROR, message);
    }
}
