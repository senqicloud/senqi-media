package com.senqicloud.senqimediaserver.handler;

import com.senqicloud.senqimediaserver.exception.*;
import com.senqicloud.senqimediaserver.response.Result;
import com.senqicloud.senqimediaserver.response.ResultCode;
import com.senqicloud.senqimediaserver.response.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局异常处理器 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 处理自定义业务异常 */
    @ExceptionHandler(BizException.class)
    public Result<?> handleBizException(BizException ex) {
        String message = ex.getMessage();
        log.warn("业务异常: {}", ex.getMessage());

        return ResultUtils.fail(ResultCode.FAIL.getCode(), message);
    }

    /** 处理没有权限异常 */
    @ExceptionHandler(ForbiddenException.class)
    public Result<?> handleForbiddenException(ForbiddenException ex) {
        String message = ex.getMessage();

        log.warn("没有权限异常: {}", ex.getMessage());

        return ResultUtils.fail(ResultCode.FORBIDDEN.getCode(), message);
    }

    /** 处理资源不存在业务异常 */
    @ExceptionHandler(NotFoundException.class)
    public Result<?> handleNotFoundException(NotFoundException ex) {
        String message = ex.getMessage();

        log.warn("资源不存在异常: {}", ex.getMessage());

        return ResultUtils.fail(ResultCode.NOT_FOUND.getCode(), message);
    }

    /** 处理服务器内部异常 */
    @ExceptionHandler(ServerErrorException.class)
    public Result<?> handleServerErrorException(ServerErrorException ex) {
        String message = ex.getMessage();

        log.warn("服务器内部异常: {}", ex.getMessage());

        return ResultUtils.fail(ResultCode.SERVER_ERROR.getCode(), message);
    }

    /** 未认证异常 */
    @ExceptionHandler(UnauthorizedException.class)
    public Result<?> handleUnauthorizedException(UnauthorizedException ex) {
        String message = ex.getMessage();

        log.warn("未认证异常: {}", ex.getMessage());

        return ResultUtils.fail(ResultCode.UNAUTHORIZED.getCode(), message);
    }
}
