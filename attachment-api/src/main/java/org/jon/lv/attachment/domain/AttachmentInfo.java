package org.jon.lv.attachment.domain;

import java.io.Serializable;

/**
 * @Description: 附件信息
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/2/22 12:57
 * version V1.0.0
 */
public class AttachmentInfo implements Serializable{
    private static final long serialVersionUID = -6719130736592991515L;

    /** 文件地址 **/
    private String url;
    /** 文件名称 **/
    private String filename;
    /** 文件大小 ---KB**/
    private int contentSize;
    /** 文件类型 **/
    private String contentType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
