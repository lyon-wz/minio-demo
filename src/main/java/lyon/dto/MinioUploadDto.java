package lyon.dto;


import lombok.Getter;

/**
 * 图片上传返回封装类
 *
 * @author wz
 * @since  2020/9/4
 */
@Getter
public class MinioUploadDto {
   private String fileName;
   private String url;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
