package com.senqicloud.senqimediaserver.enums;

public enum RedisTypeKey {
    CAPTCHA("captcha"),
    TOKEN("token"),
    LOCK("lock"),
    STATUS("status"),
    CACHE("cache");

    private final String value;

    RedisTypeKey(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
