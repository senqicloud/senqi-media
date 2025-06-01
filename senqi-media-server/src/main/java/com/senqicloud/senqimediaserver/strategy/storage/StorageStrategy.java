package com.senqicloud.senqimediaserver.strategy.storage;

import com.senqicloud.senqimediaserver.enums.StorageType;
import org.springframework.web.multipart.MultipartFile;

/**
 *  存储策略
 * */
public interface StorageStrategy {

    boolean supports(StorageType storageType);

    /**
     * 上传文件
     *
     * @param file        文件
     * @param fileName    文件名
     * @return 上传后的文件URL
     */
    String uploadFile(MultipartFile file, String fileName);
}
