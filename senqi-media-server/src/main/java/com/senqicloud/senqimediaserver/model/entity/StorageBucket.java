package com.senqicloud.senqimediaserver.model.entity;

import com.senqicloud.senqimediaserver.enums.StorageType;
import lombok.Data;

/**
 *  存储桶
 * */
@Data
public class StorageBucket {

    // 存储桶类型
    StorageType storageType;

    // 存储桶配置
    StorageConfig storageConfig;
}
