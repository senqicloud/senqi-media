package com.senqicloud.senqimediaserver.strategy.storage;

import com.senqicloud.senqimediaserver.enums.StorageType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *  阿里云 Oss 存储策略
 * */
@Service("ossStorage")
public class OssStorageStrategy implements StorageStrategy {

    @Override
    public boolean supports(StorageType storageType) {
        return storageType == StorageType.OSS;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        return "";
    }
}
