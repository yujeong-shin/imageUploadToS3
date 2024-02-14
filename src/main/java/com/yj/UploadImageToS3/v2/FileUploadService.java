package com.yj.UploadImageToS3.v2;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public String saveFile(MultipartFile file) {
        String fileUrl = "";
        try {
//          String fileName = file.getOriginalFilename();
//          String fileUrl= "https://" + bucket + "/test" +fileName;
            fileUrl = UUID.randomUUID() + "_" + file.getOriginalFilename(); //1d6d8f68-8575-435e-ab82-d3b1c2ead1f6_berry.jpg
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileUrl, file.getInputStream(), metadata);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amazonS3Client.getUrl(bucket, fileUrl).toString();
    }
}