package com.senqicloud.senqimediaserver.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.senqicloud.senqimediaserver.enums.StorageType;
import com.senqicloud.senqimediaserver.handler.StorageConfigTypeHandler;
import lombok.Data;

/** 存储桶 */
@Data
public class StorageBucket {

    @TableId(type = IdType.AUTO)
    private Long id;

    // 存储桶类型
    private StorageType storageType;

    // 存储桶名称 用于展示
    private String bucketName;

    // 存储桶描述
    private String description;

    // 容量 单位：字节，0 表示不限制，默认为 0
    private Long maxSize;

    // 使用量
    private Long usedSize;

    // 剩余容量
    private Long availableSize;

    // 状态
    private Boolean enabled; // 0 为禁用，1 为启用

    // 优先级
    private Long priority;

    // 存储桶配置
    @TableField(value = "storage_bucket_config", typeHandler = StorageConfigTypeHandler.class)
    private StorageBucketConfig storageBucketConfig;
}
