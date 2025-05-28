package com.senqicloud.senqipicserver.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserLoginResponse {
    // 登录的 JWT token
    private String token;

    // 用户的基本信息
    private UserInfoResponse userInfo;
}
