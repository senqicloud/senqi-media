package com.senqicloud.senqipicserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemConfigEnum {
    SYSTEM_NAME("SYSTEM_NAME","系统名称");

    private final String key;
    private final String value;
}
