package org.jon.lv.attachment.service;

import org.jon.lv.attachment.domain.AttachmentInfo;
import org.jon.lv.result.ResultDO;

import java.io.InputStream;

/**
 * @Description: 附件上传
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/2/22 12:56
 * version V1.0.0
 */
public interface AttachmentService {

    /**
     * 上传图片到OSS服务器  如果同名文件会覆盖服务器上的
     *
     * @param inputstream 文件流
     * @param filenameExtension 文件扩展名称
     * @return
     */
    ResultDO<AttachmentInfo> uploadImg2OSS(String filenameExtension, InputStream inputstream);

    /**
     * 上传文件到OSS服务器  如果同名文件会覆盖服务器上的
     * @param inputstream 文件流
     * @param filenameExtension 文件扩展名称
     * @return
     */
    ResultDO<AttachmentInfo> uploadFile2OSS(String filenameExtension, InputStream inputstream);

    /**
     * 上传文件到OSS服务器  如果同名文件会覆盖服务器上的
     * @param inputstream 文件流
     * @param targetFolder 目标文件夹--文件名不可用'/'开头---eg: books/story
     * @param filenameExtension 文件扩展名称
     * @return
     */
    ResultDO<AttachmentInfo> upload2OSS(String targetFolder, String filenameExtension, InputStream inputstream);
}
