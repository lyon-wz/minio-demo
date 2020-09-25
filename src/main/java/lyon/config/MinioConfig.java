package lyon.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取yml里的minio配置
 *
 * @author wz
 * @since 2020/9/2
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /***
     * minio服务器地址
     */
    public static String endpoint;

    /***
     * 存储桶名称
     */
    public static String bucketName;

    /***
     * 用户名
     */
    public static String accessKey;


    /***
     * 密码
     */
    public static String secretKey;

    public  String getEndpoint() {
        return endpoint;
    }

    public  void setEndpoint(String endpoint) {
        MinioConfig.endpoint = endpoint;
    }

    public  String getBucketName() {
        return bucketName;
    }

    public  void setBucketName(String bucketName) {
        MinioConfig.bucketName = bucketName;
    }

    public  String getAccessKey() {
        return accessKey;
    }

    public  void setAccessKey(String accessKey) {
        MinioConfig.accessKey = accessKey;
    }

    public  String getSecretKey() {
        return secretKey;
    }

    public  void setSecretKey(String secretKey) {
        MinioConfig.secretKey = secretKey;
    }
}
