package com.senqicloud.senqimediaserver.service.impl;

import com.senqicloud.senqimediaserver.exception.ServerErrorException;
import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.model.response.ImageUploadResponse;
import com.senqicloud.senqimediaserver.service.ImageService;
import com.senqicloud.senqimediaserver.service.StorageBucketService;
import com.senqicloud.senqimediaserver.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired private StorageService storageService;

    @Autowired private StorageBucketService storageBucketService;

    @Override
    public ImageUploadResponse upload(MultipartFile file) {
        // 1. 获取一个合适的存储桶
        StorageBucket storageBucket = storageBucketService.getStorageBucket(file);

        if (storageBucket == null) {
            throw new ServerErrorException("系统没有合适的存储桶！");
        }

        // 2. 调用文件上传的对应策略
        return storageService.upload(file, storageBucket);
    }
}
