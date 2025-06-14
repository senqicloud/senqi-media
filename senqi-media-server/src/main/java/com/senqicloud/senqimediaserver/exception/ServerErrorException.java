package com.senqicloud.senqimediaserver.exception;

import com.senqicloud.senqimediaserver.response.ResultCode;

/**
 * 服务器内部异常
 *
 * @author CoderMast
 */
public class ServerErrorException extends BizException {
    public ServerErrorException() {
        super(ResultCode.SERVER_ERROR);
    }

    public ServerErrorException(String message) {
        super(ResultCode.SERVER_ERROR.getCode(), message);
    }
}
