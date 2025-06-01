package com.senqicloud.senqimediaserver.strategy.login;

import com.senqicloud.senqimediaserver.enums.LoginType;
import com.senqicloud.senqimediaserver.model.request.UserLoginRequest;
import com.senqicloud.senqimediaserver.model.response.UserLoginResponse;
import com.senqicloud.senqimediaserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PasswordLoginStrategy implements LoginStrategy {

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
