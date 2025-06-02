package com.aliyun.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 创建配置文件工具类，用于读取并获取阿里云 OSS 相关的配置信息。
 * */




@ConfigurationProperties(prefix = "aliyun.oss")//实现读取配置文件
public class AliOSSProperties {

    /**
     * OSS的访问域名（Endpoint）
     * 例如：https://oss-cn-hangzhou.aliyuncs.com
     */
    private String endpoint;

    /**
     * OSS存储空间（Bucket）的名称
     */
    private String bucketName;

    /**
     * 阿里云访问密钥ID
     */
    private String accessKeyId;

    /**
     * 阿里云访问密钥Secret
     */
    private String accessKeySecret;

    /**
     * 无参构造函数
     * <p>
     * 由 Spring 框架在实例化 bean 时使用。
     */
    public AliOSSProperties() {
    }

    /**
     * 带参构造函数
     * <p>
     * 可以用于手动创建 AliOSSProperties 实例并初始化其字段。
     *
     * @param endpoint       OSS的访问域名
     * @param bucketName     OSS存储空间名称
     * @param accessKeyId    阿里云访问密钥ID
     * @param accessKeySecret 阿里云访问密钥Secret
     */
    public AliOSSProperties(String endpoint, String bucketName, String accessKeyId, String accessKeySecret) {
        this.endpoint = endpoint;
        this.bucketName = bucketName;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    /**
     * 获取
     * @return endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 设置
     * @param endpoint
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 获取
     * @return bucketName
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * 设置
     * @param bucketName
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 获取
     * @return accessKeyId
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * 设置
     * @param accessKeyId
     */
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    /**
     * 获取
     * @return accessKeySecret
     */
    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    /**
     * 设置
     * @param accessKeySecret
     */
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    /**
     * 获取
     * @return region
     */

    public String toString() {
        return "AliOSSProperties{endpoint = " + endpoint + ", bucketName = " + bucketName + ", accessKeyId = " + accessKeyId + ", accessKeySecret = " + accessKeySecret +  "}";
    }
}
