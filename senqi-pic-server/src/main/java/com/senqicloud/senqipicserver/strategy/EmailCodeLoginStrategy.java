package com.senqicloud.senqipicserver.strategy;

import com.senqicloud.senqipicserver.enums.LoginType;
import com.senqicloud.senqipicserver.model.request.UserLoginRequest;
import com.senqicloud.senqipicserver.model.response.UserLoginResponse;
import com.senqicloud.senqipicserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 邮箱验证码登录策略
@Component
public class EmailCodeLoginStrategy implements LoginStrategy {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.EMAIL_CODE;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return userService.loginByEmailCode(userLoginRequest);;
    }
}
