package com.senqicloud.senqipicserver.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserInfoResponse {
    private String username;
    private String nickname;
    private String email;
    private String phone;
}
