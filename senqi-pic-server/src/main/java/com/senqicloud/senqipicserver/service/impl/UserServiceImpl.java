package com.senqicloud.senqipicserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.senqicloud.senqipicserver.enums.UserActionType;
import com.senqicloud.senqipicserver.exception.ServerErrorException;
import com.senqicloud.senqipicserver.exception.ValidateException;
import com.senqicloud.senqipicserver.mapper.UserMapper;
import com.senqicloud.senqipicserver.model.entity.User;
import com.senqicloud.senqipicserver.model.request.UserLoginRequest;
import com.senqicloud.senqipicserver.model.request.UserRegisterRequest;
import com.senqicloud.senqipicserver.model.response.UserInfoResponse;
import com.senqicloud.senqipicserver.model.response.UserLoginResponse;
import com.senqicloud.senqipicserver.model.response.UserRegisterResponse;
import com.senqicloud.senqipicserver.service.JwtTokenService;
import com.senqicloud.senqipicserver.service.UserService;
import com.senqicloud.senqipicserver.utils.RedisKeyUtils;
import com.senqicloud.senqipicserver.utils.RedisUtils;
import com.senqicloud.senqipicserver.utils.ValidationUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        // 1. 先查看用户是否已经被注册
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userRegisterRequest.getUsername());

        User user = this.getOne(queryWrapper);

        if (user != null) {
            throw new ValidateException("用户已经存在！");
        }

        // 2. 注册用户
        User registerUser = new User();
        BeanUtils.copyProperties(userRegisterRequest, registerUser);

        boolean save = this.save(registerUser);

        if (!save) {
            throw new ServerErrorException("注册失败！");
        }

        // 3. 返回
        UserRegisterResponse retUser = new UserRegisterResponse();
        BeanUtils.copyProperties(registerUser, retUser);

        return retUser;
    }

    @Override
    public UserLoginResponse loginByAccountAndPassword(UserLoginRequest userLoginRequest) {
        // 1. 构建查询器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 判断密码
        queryWrapper.eq(User::getPassword,userLoginRequest.getPassword());

        // 2. 根据 Account 判断类型
        String account = userLoginRequest.getAccount();

        if (ValidationUtils.isEmail(account)) {
            // 邮箱 + 密码
            queryWrapper.eq(User::getEmail, account);
        } else if (ValidationUtils.isPhone(account)) {
            // 手机号 + 密码
            queryWrapper.eq(User::getPhone, account);
        } else {
            // 用户名 + 密码
            queryWrapper.eq(User::getUsername,account);
        }

        // 3. 执行登录校验
        User userLogin = this.getOne(queryWrapper);

        if (userLogin == null){
            throw new ValidateException("账号或密码错误！");
        }

        // 4. 生成登录 JWT Token
        String token = jwtTokenService.generateToken(userLogin);

        // 5. 封装返回对象
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(userLogin,userInfoResponse);

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token);
        userLoginResponse.setUserInfoResponse(userInfoResponse);

        return userLoginResponse;
    }

    @Override
    public UserLoginResponse loginBySmsCode(UserLoginRequest userLoginRequest) {
        // 1. 校验短信验证码是否匹配
        String phone = userLoginRequest.getAccount();

        String redisSmsCode = redisUtils.get(RedisKeyUtils.getSmsCaptchaKey(UserActionType.LOGIN, phone)).toString();

        if (!userLoginRequest.getCode().equals(redisSmsCode)) {
            throw new ValidateException("短信验证码错误！");
        }

        // 2. 校验成功，封装返回数据

        // 3. 查询数据库，获取用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, phone);

        User user = this.getOne(queryWrapper);

        // 4. 获取 JWT Token
        String token = jwtTokenService.generateToken(user);

        // 5. 封装 UserInfoResponse
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(user, userInfoResponse);

        // 6. 封装返回对象
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token);
        userLoginResponse.setUserInfoResponse(userInfoResponse);

        return userLoginResponse;
    }

    @Override
    public UserLoginResponse loginByEmailCode(UserLoginRequest userLoginRequest) {
        // 1. 校验邮箱验证码是否匹配
        String email = userLoginRequest.getAccount();

        String redisEmailCode = redisUtils.get(RedisKeyUtils.getSmsCaptchaKey(UserActionType.LOGIN, email)).toString();

        if (!userLoginRequest.getCode().equals(redisEmailCode)) {
            throw new ValidateException("邮箱验证码错误！");
        }

        // 2. 校验成功，封装返回数据

        // 3. 查询数据库，封装 UserInfoResponse
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);

        User user = this.getOne(queryWrapper);

        String token = jwtTokenService.generateToken(user);

        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(user, userInfoResponse);

        // 4. 封装返回对象
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setToken(token);
        userLoginResponse.setUserInfoResponse(userInfoResponse);

        return userLoginResponse;
    }
}
