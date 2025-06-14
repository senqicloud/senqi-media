package com.senqicloud.senqimediaserver.exception;

import com.senqicloud.senqimediaserver.response.ResultCode;

/**
 * 资源未找到异常
 *
 * @author CoderMast
 */
public class NotFoundException extends BizException {
    public NotFoundException() {
        super(ResultCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ResultCode.NOT_FOUND.getCode(), message);
    }
}
