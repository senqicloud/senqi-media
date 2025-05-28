package com.senqicloud.senqipicserver.strategy;

import com.senqicloud.senqipicserver.enums.LoginType;
import com.senqicloud.senqipicserver.model.request.UserLoginRequest;
import com.senqicloud.senqipicserver.model.response.UserLoginResponse;
import com.senqicloud.senqipicserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordLoginStrategy implements LoginStrategy{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.PASSWORD;
    }

    @Override
    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        return userService.loginByAccountAndPassword(userLoginRequest);
    }


}
