package org.jon.lv.attachment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description: 路径工具类
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 20:27
 * version V1.0.0
 */
public class PathUtil {

    public final static String DATE_FORMAT = "yyyyMMddHHmm";
    public final static String DATE_FORMAT_PATH = "/yyyy/MM/dd/";

    public static Random random = new Random();

    /**
      * @Description: 格式化当前世界
      * @Title formatCurrentDate
      * @Author  lv bin
      * @Date 2016/9/25 20:42
      */
    public static String formatCurrentDate(String format){

        return new SimpleDateFormat(format).format(new Date());
    }

    public static String createImgName(String filenameExtension){
        return  "IMG_" + System.currentTimeMillis() + "_" + random.nextInt(100000) + filenameExtension;
    }

    /**
     * 生成文件名称
     * @param filenameExtension
     * @return
     */
    public static String createFileName(String filenameExtension){
        return "FILE_" + System.currentTimeMillis() + "_" + random.nextInt(100000) + filenameExtension;
    }
    public static void main(String[] args) {
        System.out.println(createFileName(".xml"));
    }

}
