package com.senqicloud.senqipicserver.model.response;

import lombok.Data;

// 用户注册响应
@Data
public class UserRegisterResponse {

    // 用户名
    private String username;

    // 昵称
    private String nickname;

    // 邮箱
    private String email;
}
