package com.senqicloud.senqimediaserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *  图片管理
 * */
@RestController
@RequestMapping("/api/images/")
public class ImageController {

    /**
     *  图片上传
     * */
    @PostMapping()
    public void upload(@RequestParam("file") MultipartFile file) {

    }
}
