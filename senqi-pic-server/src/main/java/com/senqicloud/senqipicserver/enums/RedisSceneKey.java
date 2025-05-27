package com.senqicloud.senqipicserver.enums;

public enum RedisSceneKey {
    REGISTER("register"),
    LOGIN("login"),
    RESET("reset"),
    IMAGE("image"), // 图形验证码
    EMAIL("email"), // 邮箱验证码
    SMS("sms");     // 短信验证码

    private final String value;

    RedisSceneKey(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}