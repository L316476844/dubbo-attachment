package org.jon.lv.attachment.oss;

/**
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 15:39
 * version V1.0.0
 */
public class OssConfigure {

    private String endpoint; // 根地址
    private String accessId; // 账户id
    private String accessSecret;// 密码
    private String bucketNameDefault; // 第一层文件夹的名字-分盘
    private String accessUrl;// 用户访问请求地址前缀
    private String imgFolder;// 图片保存文件夹名称
    private String fileFolder;// 文件保存文件夹名称

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getBucketNameDefault() {
        return bucketNameDefault;
    }

    public void setBucketNameDefault(String bucketNameDefault) {
        this.bucketNameDefault = bucketNameDefault;
    }

    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    public String getImgFolder() {
        return imgFolder;
    }

    public void setImgFolder(String imgFolder) {
        this.imgFolder = imgFolder;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }
}
