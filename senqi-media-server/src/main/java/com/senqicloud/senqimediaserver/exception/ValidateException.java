package com.senqicloud.senqimediaserver.exception;


import com.senqicloud.senqimediaserver.response.ResultCode;

/**
 * 参数校验失败异常
 * @author CoderMast
 */

public class ValidateException extends BizException {
    public ValidateException() {
        super(ResultCode.VALIDATE_FAILED);
    }

    public ValidateException(String message) {
        super(ResultCode.VALIDATE_FAILED.getCode(), message);
    }
}