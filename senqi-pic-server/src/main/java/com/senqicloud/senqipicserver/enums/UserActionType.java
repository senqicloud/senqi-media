package com.senqicloud.senqipicserver.enums;

public enum UserActionType {
    REGISTER,       // 注册
    LOGIN,          // 登录
    LOGOUT,         // 登出
    RESET_PASSWORD, // 重置密码
    SEND_VERIFICATION_CODE, // 发送验证码
    VERIFY_CODE     // 验证验证码
}