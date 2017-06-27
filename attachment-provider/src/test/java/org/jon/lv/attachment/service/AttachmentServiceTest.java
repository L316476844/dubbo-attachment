package org.jon.lv.attachment.service;

import com.alibaba.fastjson.JSON;
import org.jon.lv.attachment.JunitBaseTest;
import org.jon.lv.attachment.domain.AttachmentInfo;
import org.jon.lv.result.ResultDO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * @Package com.shfc.attachment.service.AttachmentServiceTest
 * @Description: 测试代码
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/2/22 14:51
 * version V1.0.0
 */
public class AttachmentServiceTest extends JunitBaseTest {

    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void testUploadImg2OSS() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("d:\\41654.jpg"));

        ResultDO<AttachmentInfo> resultDO =  attachmentService.uploadImg2OSS(".jpg", inputStream);

        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testUploadFile2OSS() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("d:\\test.pdf"));

        ResultDO<AttachmentInfo> resultDO =  attachmentService.uploadFile2OSS(".pdf", inputStream);

        System.out.println(JSON.toJSONString(resultDO));
    }

    @Test
    public void testUpload2OSS() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("d:\\test.pdf"));

        ResultDO<AttachmentInfo> resultDO =  attachmentService.upload2OSS("test", ".pdf", inputStream);

        System.out.println(JSON.toJSONString(resultDO));
    }
}
