package com.senqicloud.senqimediaserver.service;

import com.senqicloud.senqimediaserver.model.entity.StorageBucket;
import com.senqicloud.senqimediaserver.model.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    ImageUploadResponse upload(MultipartFile file, StorageBucket storageBucket);
}
