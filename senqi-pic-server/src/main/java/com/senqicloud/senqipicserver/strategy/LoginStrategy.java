package com.senqicloud.senqipicserver.strategy;

import com.senqicloud.senqipicserver.enums.LoginType;
import com.senqicloud.senqipicserver.model.request.UserLoginRequest;
import com.senqicloud.senqipicserver.model.response.UserLoginResponse;

// 登录策略
public interface LoginStrategy {

    boolean supports(LoginType loginType);

    UserLoginResponse login(UserLoginRequest userLoginRequest);
}
