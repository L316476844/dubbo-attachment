package org.jon.lv.attachment.service;

import com.aliyun.oss.model.ObjectMetadata;
import org.apache.log4j.Logger;
import org.jon.lv.attachment.domain.AttachmentInfo;
import org.jon.lv.attachment.utils.ContentTypeUtils;
import org.jon.lv.attachment.utils.PathUtil;
import org.jon.lv.attachment.oss.AttachmentFilter;
import org.jon.lv.attachment.oss.OSSClientUtils;
import org.jon.lv.attachment.oss.OssConfigure;
import org.jon.lv.result.ResultDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * @Description: 附件
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/2/22 13:17
 * version V1.0.0
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private OssConfigure ossConfigure;

    @Autowired
    private AttachmentFilter attachmentFilter;

    public final static Logger LOGGER = Logger.getLogger(AttachmentServiceImpl.class);

    private ObjectMetadata objectMetadata = null;

    // 设置URL过期时间为10年  3600l* 1000*24*365*10
    private Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 10);

    private int MSIZE = 1024 * 1024;

    @PostConstruct
    public void init() {
        objectMetadata = new ObjectMetadata();
        // 初始化 ObjectMetadata 对象
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
    }

    @Override
    public ResultDO<AttachmentInfo> uploadImg2OSS(String filenameExtension, InputStream inputstream) {

        ResultDO<AttachmentInfo> resultDO = new ResultDO<AttachmentInfo>();

        if (StringUtils.isEmpty(filenameExtension)) {
            resultDO.setErrMsg("文件后缀名为空");
            return resultDO;
        }

        if (!attachmentFilter.getPicFilters().contains(filenameExtension.toLowerCase())) {
            resultDO.setErrMsg("不能识别此类图片");
            return resultDO;
        }

        return upload2OSS(ossConfigure.getImgFolder(), filenameExtension, inputstream);
    }

    @Override
    public ResultDO<AttachmentInfo> uploadFile2OSS(String filenameExtension, InputStream inputstream) {
        ResultDO<AttachmentInfo> resultDO = new ResultDO<AttachmentInfo>();
        if (StringUtils.isEmpty(filenameExtension)) {
            resultDO.setErrMsg("文件后缀名为空");
            return resultDO;
        }

        if (!attachmentFilter.getFileFilters().contains(filenameExtension.toLowerCase())) {
            resultDO.setErrMsg("不能识别此类文件");
            return resultDO;
        }

        return upload2OSS(ossConfigure.getFileFolder(), filenameExtension, inputstream);
    }

    @Override
    public ResultDO<AttachmentInfo> upload2OSS(String targetFolder, String filenameExtension, InputStream inputstream) {

        ResultDO<AttachmentInfo> resultDO = new ResultDO<AttachmentInfo>();
        try {

            ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
            int ch;
            while((ch = inputstream.read() ) != -1){
                bAOutputStream.write(ch);
            }

            byte data [] =bAOutputStream.toByteArray();
            bAOutputStream.close();

            if (StringUtils.isEmpty(filenameExtension)) {
                resultDO.setErrMsg("文件后缀名为空");
                return resultDO;
            }

            int contentLength = data.length;

            // 小写 扩展名
            filenameExtension = filenameExtension.trim().toLowerCase();

            if(inputstream == null || contentLength == 0){
                resultDO.setErrMsg("流文件为空");
                return resultDO;
            }

            if (!attachmentFilter.getPicSuffixList().contains(filenameExtension) &&
                    !attachmentFilter.getFileSuffixList().contains(filenameExtension)) {
                resultDO.setErrMsg("不能识别此类文件");
                return resultDO;
            }

            // 图片限制大小-字节
            int imageSize = attachmentFilter.getPicLimit() * MSIZE;
            // 文件限制大小-字节
            int fileSize = attachmentFilter.getFileLimit() * MSIZE;
            // 文件名称
            String fileName = "";

            if(attachmentFilter.getPicSuffixList().contains(filenameExtension)){
                if(contentLength > imageSize){
                    resultDO.setErrMsg("超过图片限制大小" + attachmentFilter.getPicLimit() + "M");
                    return resultDO;
                }

                fileName = PathUtil.createImgName(filenameExtension);
            }else{
                if(contentLength > fileSize){
                    resultDO.setErrMsg("超过文件限制大小" + attachmentFilter.getFileLimit() + "M");
                    return resultDO;
                }

                fileName = PathUtil.createFileName(filenameExtension);
            }

            if (StringUtils.isEmpty(targetFolder)) {
                if (attachmentFilter.getPicSuffixList().contains(filenameExtension)) {
                    targetFolder = ossConfigure.getImgFolder();
                } else {
                    targetFolder = ossConfigure.getFileFolder();
                }
            }
            String fullPath = targetFolder + PathUtil.formatCurrentDate(PathUtil.DATE_FORMAT_PATH);

            //创建上传Object的Metadata
            objectMetadata.setContentLength(contentLength);
            objectMetadata.setContentType(ContentTypeUtils.contentType(filenameExtension));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);



            OSSClientUtils.getOSSClient().putObject(ossConfigure.getBucketNameDefault(), fullPath + fileName, new ByteArrayInputStream(data), objectMetadata);
            String url = getImgUrl(ossConfigure.getBucketNameDefault(), fileName, fullPath);

            AttachmentInfo info = new AttachmentInfo();
            info.setContentType(filenameExtension);
            info.setContentSize(contentLength / 1024);
            info.setFilename(fileName);
            info.setUrl(url);
            resultDO.setData(info);
            resultDO.setSuccess(true);
        } catch (Exception e) {
            resultDO.setErrMsg("上传阿里云异常");
            e.printStackTrace();
            LOGGER.error("上传阿里云异常---" + e.getMessage(), e);
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
                OSSClientUtils.destory();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        return resultDO;
    }

    /**
     * 获得图片路径
     *
     * @param bucketName
     * @param fileName
     * @param fullPath
     * @return
     */
    public String getImgUrl(String bucketName, String fileName, String fullPath) {
        if (!StringUtils.isEmpty(fileName)) {
            String[] split = fileName.split("/");
            return getUrl(fullPath + split[split.length - 1], bucketName);
        }
        return null;
    }


    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    private String getUrl(String key, String bucketName) {
        // 生成URL
        URL url = OSSClientUtils.getOSSClient().generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString().split("\\?")[0];
        }

        return null;
    }
}
