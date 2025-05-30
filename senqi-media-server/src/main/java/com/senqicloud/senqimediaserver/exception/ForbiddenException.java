package com.senqicloud.senqimediaserver.exception;


import com.senqicloud.senqimediaserver.response.ResultCode;

/**
 * 没有权限异常
 * @author CoderMast
 */


public class ForbiddenException extends BizException {
    public ForbiddenException() {
        super(ResultCode.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(ResultCode.FORBIDDEN.getCode(), message);
    }
}