package com.senqicloud.senqimediaserver.enums;

public enum RedisModuleKey {
    AUTH("auth"),
    USER("user"),
    ORDER("order"),
    CDN("cdn"),
    CONFIG("config");

    private final String value;

    RedisModuleKey(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}