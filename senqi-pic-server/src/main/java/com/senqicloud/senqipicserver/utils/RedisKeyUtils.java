package com.senqicloud.senqipicserver.utils;

import com.senqicloud.senqipicserver.enums.RedisSceneKey;
import com.senqicloud.senqipicserver.enums.RedisTypeKey;
import com.senqicloud.senqipicserver.enums.RedisModuleKey;

public class RedisKeyUtils {


    // 邮箱验证码（如注册、登录等场景）
    public static String getEmailCaptchaKey(String scene, String email) {
        return String.format("%s:%s:%s:%s:%s", RedisModuleKey.AUTH, RedisTypeKey.CAPTCHA, scene, RedisSceneKey.EMAIL, email);
    }

    // 图形验证码（比如验证码uuid）
    public static String getImageCaptchaKey(String uuid) {
        return String.format("%s:%s:%s:%s", RedisModuleKey.AUTH, RedisTypeKey.CAPTCHA, RedisSceneKey.EMAIL, uuid);
    }

    // 通用验证码 key 构造器（自定义场景 + 类型 + 接收者）
    public static String getCaptchaKey(String module,String scene, String type, String receiver) {
        return String.format("%s:%s:%s:%s:%s", module, RedisTypeKey.CAPTCHA, scene, type, receiver);
    }
}

