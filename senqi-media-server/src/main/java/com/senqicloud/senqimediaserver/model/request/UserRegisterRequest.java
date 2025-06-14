package com.senqicloud.senqimediaserver.model.request;

import com.senqicloud.senqimediaserver.validator.PasswordMatches;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

// 用户注册请求体
@Data
@PasswordMatches
public class UserRegisterRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在 3~20 位之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 32, message = "密码长度必须在 6~32 位之间")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String confirmPassword;

    // 验证码
    @NotBlank(message = "邮箱验证码不能为空")
    private String code;

    // 图形验证码 ID
    @NotBlank(message = "图形验证码 ID")
    private String captchaId;

    // 图形验证码
    @NotBlank(message = "图形验证码不能为空")
    private String captchaCode;
}
