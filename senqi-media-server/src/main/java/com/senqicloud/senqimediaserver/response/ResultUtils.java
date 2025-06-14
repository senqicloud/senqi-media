package com.senqicloud.senqimediaserver.response;

public class ResultUtils {

    /** 成功 - 无数据返回 */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    /** 成功 - 有数据返回 */
    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    /** 成功 - 有数据返回 */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    /** 成功 - 有数据返回 */
    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /** 失败 - 自定义失败信息 */
    public static <T> Result<T> fail(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /** 自定义状态码和消息 */
    public static <T> Result<T> build(ResultCode codeEnum) {
        return new Result<>(codeEnum);
    }

    /** 自定义状态码、消息、数据 */
    public static <T> Result<T> build(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }
}
