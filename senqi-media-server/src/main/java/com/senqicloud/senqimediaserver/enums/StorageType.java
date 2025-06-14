package com.senqicloud.senqimediaserver.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 存储桶类型 */
@Getter
@AllArgsConstructor
public enum StorageType {
    FTP("FTP", "文件传输协议"),
    SFTP("SFTP", "安全文件传输协议"),
    COS("COS", "腾讯云对象存储服务"),
    OSS("OSS", "阿里云对象存储服务"),
    S3("S3", "亚马逊简单存储服务"),
    QINIU("Qiniu", "七牛云存储"),
    WEBDAV("WebDAV", "Web分布式创作和版本控制");

    // 类型编码
    private final String code;

    // 类型名称
    private final String name;
}
