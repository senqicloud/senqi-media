package com.senqicloud.senqipicserver.model.response;

import lombok.Data;

@Data
public class UserInfoResponse {

    // 用户名
    private String username;

    // 昵称
    private String nickname;

    // 邮箱
    private String email;

    // 手机号
    private String phone;
}
