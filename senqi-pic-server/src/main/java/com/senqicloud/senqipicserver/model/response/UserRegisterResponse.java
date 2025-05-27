package com.senqicloud.senqipicserver.model.response;

import lombok.Data;

// 用户注册响应
@Data
public class UserRegisterResponse {
    private String username;

    private String nickname;

    private String email;
}
