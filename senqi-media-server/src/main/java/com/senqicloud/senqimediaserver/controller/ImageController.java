package com.senqicloud.senqimediaserver.controller;

import com.senqicloud.senqimediaserver.exception.ValidateException;
import com.senqicloud.senqimediaserver.model.response.ImageUploadResponse;
import com.senqicloud.senqimediaserver.service.ImageService;
import com.senqicloud.senqimediaserver.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *  图片管理
 * */
@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    /**
     *  图片上传
     * */
    @PostMapping("/upload")
    public ImageUploadResponse upload(@RequestParam("file") MultipartFile file) {
        // 1. 校验文件格式是否为图片
        if (file.isEmpty()) {
            throw new ValidateException("图片不能为空！");
        }

        // 文件格式不是图片
        if (!FileUtils.isImage(file)) {
            throw new ValidateException("文件格式不是图片！");
        }

        // 2. 调用实际文件上传逻辑
        return imageService.upload(file);
    }
}
