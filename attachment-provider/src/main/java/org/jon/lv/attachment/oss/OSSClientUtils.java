package org.jon.lv.attachment.oss;

import com.aliyun.oss.OSSClient;

/**
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 21:39
 * version V1.0.0
 */
public class OSSClientUtils {
    private static OssConfigure ossConfigure;

    public static OssConfigure getOssConfigure() {
        return ossConfigure;
    }

    private static OSSClient ossClient;

    /**
     *设置 ossConfigure
     * @param ossConfigure
     */
    public static void setOssConfigure(OssConfigure ossConfigure) {
        OSSClientUtils.ossConfigure = ossConfigure;

        if(ossClient == null){
            ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessId(),
                    ossConfigure.getAccessSecret());
        }
    }

    /**
     * 获取oss client
     * @return OSSClient
     */
    public static OSSClient getOSSClient(){
        if(ossClient == null){
            ossClient = new OSSClient(ossConfigure.getEndpoint(), ossConfigure.getAccessId(),
                    ossConfigure.getAccessSecret());
        }

        return ossClient;
    }

    /**
     * 销毁
     */
    public static void destory(){
        if(ossClient != null){
            ossClient.shutdown();
            ossClient = null;
        }
    }
}
