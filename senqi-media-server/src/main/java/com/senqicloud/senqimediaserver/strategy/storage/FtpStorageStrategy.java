package com.senqicloud.senqimediaserver.strategy.storage;

import com.senqicloud.senqimediaserver.enums.StorageType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FTP 存储策略
 */
@Service("ftpStorage")
public class FtpStorageStrategy implements StorageStrategy {

    @Override
    public boolean supports(StorageType storageType) {
        return storageType == StorageType.FTP;
    }

    @Override
    public String uploadFile(MultipartFile file, String fileName) {

        return "";
    }
}
