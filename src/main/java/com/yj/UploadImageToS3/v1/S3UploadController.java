package com.yj.UploadImageToS3.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
@RestController
public class S3UploadController {
    private final S3UploadService s3UploadService;

    @PostMapping("/s3/test")
    public String saveFileToS3(@RequestParam(value = "file") MultipartFile multipartFile){
        return s3UploadService.saveFile(multipartFile);
    }


}
