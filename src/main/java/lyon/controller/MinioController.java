package lyon.controller;


import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import lyon.config.MinioConfig;
import lyon.dto.MinioUploadDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片上传控制层
 *
 * @author wz
 */
@RestController
@RequestMapping("/minio")
@Slf4j
public class MinioController {


    @PostMapping(value = "/upload")
    public MinioUploadDto upload(@RequestParam("file") MultipartFile file) {
        try {
            MinioClient minioClient = new MinioClient(MinioConfig.endpoint, MinioConfig.accessKey, MinioConfig.secretKey);
            if (minioClient.bucketExists(MinioConfig.bucketName)){
               log.info("存储桶已存在");
            }else {
                //创建存储桶
                minioClient.makeBucket(MinioConfig.bucketName);
                //设置只读权限
                minioClient.setBucketPolicy(MinioConfig.bucketName,"*.*", PolicyType.READ_ONLY);
            }
            String fileName=file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //根据object / 生成文件夹
            String objectName=sdf.format(new Date())+"/"+fileName;
            minioClient.putObject(MinioConfig.bucketName,objectName,file.getInputStream(),file.getContentType());
            MinioUploadDto minioUploadDto=new MinioUploadDto();
            minioUploadDto.setFileName(fileName);
            minioUploadDto.setUrl(MinioConfig.endpoint+"/"+MinioConfig.bucketName+"/"+objectName);
            return  minioUploadDto;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}