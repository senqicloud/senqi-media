package com.senqicloud.senqipicserver.model.request;

import com.senqicloud.senqipicserver.enums.LoginType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserLoginRequest {
    // 账号 [用户名、手机号、邮箱]
    @Size(min = 3, max = 20, message = "账号长度必须在 3~20 位之间")
    private String account;

    // 密码
    @Size(min = 6, max = 32, message = "密码长度必须在 6~32 位之间")
    private String password;

    // 图形验证码 ID
    @NotBlank(message = "图形验证码 ID")
    private String captchaId;

    // 图形验证码
    @NotBlank(message = "图形验证码不能为空")
    private String captchaCode;

    // 验证码
    @NotBlank(message = "验证码不能为空")
    private String code;

    // 登录类型
    @NotNull(message = "登录类型不能为空")
    private LoginType loginType;
}
