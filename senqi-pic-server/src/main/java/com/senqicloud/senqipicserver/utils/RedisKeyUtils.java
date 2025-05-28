package com.senqicloud.senqipicserver.utils;

import com.senqicloud.senqipicserver.enums.RedisSceneKey;
import com.senqicloud.senqipicserver.enums.RedisTypeKey;
import com.senqicloud.senqipicserver.enums.RedisModuleKey;
import com.senqicloud.senqipicserver.enums.UserActionType;

public class RedisKeyUtils {


    // 邮箱验证码（如注册、登录等场景）
    public static String getEmailCaptchaKey(UserActionType userActionType, String email) {
        return String.format("%s:%s:%s:%s:%s", RedisModuleKey.AUTH, RedisTypeKey.CAPTCHA, userActionType, RedisSceneKey.EMAIL, email);
    }

    // 短信验证码（如注册、登录等场景）
    public static String getSmsCaptchaKey(UserActionType userActionType, String phone) {
        return String.format("%s:%s:%s:%s:%s", RedisModuleKey.AUTH, RedisTypeKey.CAPTCHA, userActionType, RedisSceneKey.SMS, phone);
    }

    // 图形验证码（比如验证码uuid）
    public static String getImageCaptchaKey(String uuid) {
        return String.format("%s:%s:%s:%s", RedisModuleKey.AUTH, RedisTypeKey.CAPTCHA, RedisSceneKey.EMAIL, uuid);
    }

    // 通用验证码 key 构造器（自定义场景 + 类型 + 接收者）
    public static String getCaptchaKey(String module,String scene, String type, String receiver) {
        return String.format("%s:%s:%s:%s:%s", module, RedisTypeKey.CAPTCHA, scene, type, receiver);
    }

    // JWT Token key
    public static String getJwtTokenKey(String jti){
        return String.format("%s:%s:%s","JWT","BLACKLIST",jti);
    }
}

