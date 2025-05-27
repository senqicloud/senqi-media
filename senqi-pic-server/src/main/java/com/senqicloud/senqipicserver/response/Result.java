package com.senqicloud.senqipicserver.response;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public Result(ResultCode codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
    }

    public Result(ResultCode codeEnum,String msg) {
        this.code = codeEnum.getCode();
        this.msg = msg;
    }

    public Result(ResultCode codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMessage();
        this.data = data;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

}
