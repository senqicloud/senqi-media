package com.senqicloud.senqimediaserver.strategy.login;

import com.senqicloud.senqimediaserver.enums.LoginType;
import com.senqicloud.senqimediaserver.model.request.UserLoginRequest;
import com.senqicloud.senqimediaserver.model.response.UserLoginResponse;

// 登录策略
public interface LoginStrategy {

    boolean supports(LoginType loginType);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
