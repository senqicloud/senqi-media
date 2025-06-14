package com.senqicloud.senqimediaserver.model.entity;

import com.senqicloud.senqimediaserver.enums.StorageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

public abstract class StorageBucketConfig {
    public StorageType storageType;
}

@Data
@EqualsAndHashCode(callSuper = true)
class OssConfig extends StorageBucketConfig {}

@Data
@EqualsAndHashCode(callSuper = true)
class FtpConfig extends StorageBucketConfig {}

@Data
@EqualsAndHashCode(callSuper = true)
class CosConfig extends StorageBucketConfig {
    private String region;
    private String secretId;
    private String secretKey;
    private String bucketName;
}
