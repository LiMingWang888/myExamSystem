package com.wlm.exam.exception;

public abstract class BaseException extends RuntimeException {
    private final ErrorCode error;
    private final Object data;

    public BaseException(ErrorCode error, Object data) {
        super(error.getMessage());
        this.error = error;
        this.data = data;
    }

    protected BaseException(ErrorCode error, Object data, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
        this.data = data;
    }

    public ErrorCode getError() {
        return error;
    }

    public Object getData() {
        return data;
    }

}