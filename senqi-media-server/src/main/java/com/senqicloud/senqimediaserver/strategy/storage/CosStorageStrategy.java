package com.senqicloud.senqimediaserver.strategy.storage;

import com.senqicloud.senqimediaserver.enums.StorageType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** 腾讯云 COS 存储策略 */
@Service("cosStorage")
public class CosStorageStrategy implements StorageStrategy {

    @Override
    public boolean supports(StorageType storageType) {
        return storageType == StorageType.COS;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {
        return "";
    }
}
