package org.jon.lv.attachment.oss;

/**
 * @Description: org.jon.lv.attachment.oss client 初始化
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 21:32
 * version V1.0.0
 */
public class Initialization {
    private OssConfigure ossConfigure;

    public OssConfigure getOssConfigure() {
        return ossConfigure;
    }

    public void setOssConfigure(OssConfigure ossConfigure) {
        this.ossConfigure = ossConfigure;

        OSSClientUtils.setOssConfigure(ossConfigure);
    }

}
