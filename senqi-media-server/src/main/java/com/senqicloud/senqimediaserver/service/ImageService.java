package com.senqicloud.senqimediaserver.service;

import com.senqicloud.senqimediaserver.model.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageUploadResponse upload(MultipartFile file);
}
