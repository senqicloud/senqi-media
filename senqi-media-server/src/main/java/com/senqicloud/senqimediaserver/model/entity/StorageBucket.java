package com.senqicloud.senqimediaserver.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.senqicloud.senqimediaserver.enums.StorageType;
import lombok.Data;

/**
 *  存储桶
 * */
@Data
public class StorageBucket {

    @TableId(type = IdType.AUTO)  // 这里的IdType根据你的主键策略选择，可以是AUTO、INPUT等
    private Long id;

    // 存储桶类型
    StorageType storageType;

    // 存储桶配置
    StorageConfig storageConfig;
}
