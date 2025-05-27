package com.senqicloud.senqipicserver.exception;


import com.senqicloud.senqipicserver.response.ResultCode;
import lombok.Getter;

/**
 * 自定义业务异常类，系统中所有业务异常的父类。
 * 可通过构造函数传入错误码和消息，用于统一异常处理。
 */

@Getter
public class BizException extends RuntimeException {
    private final Integer code;

    public BizException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String message) {
        super(message);
        this.code = ResultCode.FAIL.getCode();
    }
}