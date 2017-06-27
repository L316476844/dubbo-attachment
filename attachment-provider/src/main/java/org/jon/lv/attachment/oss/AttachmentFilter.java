package org.jon.lv.attachment.oss;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2016/9/25 16:12
 * version V1.0.0
 */
public class AttachmentFilter {
    private final static String SPLIT_SYMBOL = ",";
    private String picFilters; // 图片过滤后缀
    private String fileFilters; // 文件后缀
    private int picLimit = 3;  // 图片大小限制
    private int fileLimit = 10;  // 文件大小限制

    private List<String> picSuffixList; // 图片后缀
    private List<String> fileSuffixList; // 文件后缀

    public String getPicFilters() {
        return picFilters;
    }

    public void setPicFilters(String picFilters) {
        this.picFilters = picFilters;
        if(!StringUtils.isEmpty(picFilters)){
            this.picSuffixList = Arrays.asList(picFilters.split(SPLIT_SYMBOL));
        }
    }

    public String getFileFilters() {
        return fileFilters;
    }

    public void setFileFilters(String fileFilters) {
        this.fileFilters = fileFilters;
        if(!StringUtils.isEmpty(fileFilters)){
            this.fileSuffixList = Arrays.asList(fileFilters.split(SPLIT_SYMBOL));
        }
    }

    public List<String> getPicSuffixList() {
        return picSuffixList;
    }

    public void setPicSuffixList(List<String> picSuffixList) {
        this.picSuffixList = picSuffixList;
    }

    public List<String> getFileSuffixList() {
        return fileSuffixList;
    }

    public void setFileSuffixList(List<String> fileSuffixList) {
        this.fileSuffixList = fileSuffixList;
    }

    public void setPicLimit(int picLimit) {
        this.picLimit = picLimit;
    }

    public void setFileLimit(int fileLimit) {
        this.fileLimit = fileLimit;
    }

    public int getPicLimit() {
        return picLimit;
    }

    public int getFileLimit() {
        return fileLimit;
    }
}
