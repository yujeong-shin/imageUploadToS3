package com.yj.UploadImageToS3.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping("/upload")
// "Current Request is not a multipart request" 에러 때문에 추가했지만 그냥 RequestMapping으로 충분한데??
//@RequestMapping(value = "/upload",
//        method = RequestMethod.POST,
//        consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) { //uploadFile(@RequestParam(value = "file") MultipartFile file)도 동일
        String fileUrl = fileUploadService.saveFile(file);
        return ResponseEntity.ok(fileUrl);
        // s3에 저장되는 객체 URL 값 리턴
        // https://mywellcombucket.s3.ap-northeast-2.amazonaws.com/5edfdd6a-17de-454d-8261-c63363b41716_berry.jpg
    }
}