package com.senqicloud.senqimediaserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemConfigType {
    SYSTEM_NAME("SYSTEM_NAME","系统名称");

    private final String key;
    private final String value;

    /**
     * 判断传入的 key 是否存在于枚举中
     */
    public static boolean containsKey(String key) {
        for (SystemConfigType config : values()) {
            if (config.getKey().equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }
}
