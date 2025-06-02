package com.aliyun.oss;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 * <p>
 * 该类提供方法用于将文件上传到阿里云 OSS。
 */
public class AliOSSUtils {

    private AliOSSProperties aliyunOSSProperties;

    public AliOSSUtils() {
    }

    public AliOSSUtils(AliOSSProperties aliyunOSSProperties) {
        this.aliyunOSSProperties = aliyunOSSProperties;
    }


    /**
     * 实现上传图片到 OSS
     *
     * @param file 上传的文件
     * @return 文件在 OSS 上的访问 URL
     * @throws IOException 文件读取异常
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取阿里云 OSS 参数
        String endpoint = aliyunOSSProperties.getEndpoint();
        String accessKeyId = aliyunOSSProperties.getAccessKeyId();
        String accessKeySecret = aliyunOSSProperties.getAccessKeySecret();
        String bucketName = aliyunOSSProperties.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖，生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        // 创建 OSS 客户端并上传文件
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossClient.putObject(bucketName, fileName, inputStream);
        } catch (Exception e) {
            // 处理可能的上传异常
            throw new RuntimeException("文件上传到 OSS 失败", e);
        } finally {
            // 确保 OSS 客户端被关闭以释放资源
            ossClient.shutdown();
        }

        // 构建文件访问路径
        // 注意：这里假设 endpoint 格式为 "https://xxx"，需要根据实际情况调整
        String url = "https://" + bucketName + "." +
                endpoint.replace("https://", "") + "/" + fileName;
        System.out.println("文件上传到 OSS 的文件路径：" + url);

        // 返回上传到 OSS 的文件路径
        return url;
    }

    /**
     * 获取
     * @return aliyunOSSProperties
     */
    public AliOSSProperties getAliyunOSSProperties() {
        return aliyunOSSProperties;
    }

    /**
     * 设置
     * @param aliyunOSSProperties
     */
    public void setAliyunOSSProperties(AliOSSProperties aliyunOSSProperties) {
        this.aliyunOSSProperties = aliyunOSSProperties;
    }

    public String toString() {
        return "AliOSSUtils{aliyunOSSProperties = " + aliyunOSSProperties + "}";
    }
}