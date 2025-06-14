package com.senqicloud.senqimediaserver.strategy.login;

import com.senqicloud.senqimediaserver.enums.LoginType;
import com.senqicloud.senqimediaserver.model.request.UserLoginRequest;
import com.senqicloud.senqimediaserver.model.response.UserLoginResponse;
import com.senqicloud.senqimediaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 短信验证码登录策略
@Component
public class SmsCodeLoginStrategy implements LoginStrategy {

    @Autowired private UserService userService;

    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.SMS_CODE;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return userService.loginBySmsCode(userLoginRequest);
    }
}
