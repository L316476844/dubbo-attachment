package org.jon.lv.attachment.utils;

/**
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 17:51
 * version V1.0.0
 */
public class ContentTypeUtils {

    /**
     * Description: 判断OSS服务文件上传时文件的contentType
     * @Version1.0
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String contentType(String filenameExtension){
        filenameExtension = filenameExtension.toUpperCase();
        if(filenameExtension.equals(".BMP")){return "image/bmp";}
        if(filenameExtension.equals(".GIF")){return "image/gif";}
        if(filenameExtension.equals(".JPEG")||
                filenameExtension.equals(".JPG")||
                filenameExtension.equals(".PNG")){return "image/jpeg";}
        if(filenameExtension.equals(".HTML")){return "text/html";}
        if(filenameExtension.equals(".TXT")){return "text/plain";}
        if(filenameExtension.equals(".VSD")){return "application/vnd.visio";}
        if(filenameExtension.equals(".PPTX")||
                filenameExtension.equals(".PPT")){return "application/vnd.ms-powerpoint";}
        if(filenameExtension.equals(".DOCX")||
                filenameExtension.equals(".DOC")){return "application/msword";}
        if(filenameExtension.equals(".XML")){return "text/xml";}

        return "text/html";
    }
}
