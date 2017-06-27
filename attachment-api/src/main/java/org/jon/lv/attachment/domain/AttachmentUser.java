package org.jon.lv.attachment.domain;

import java.io.Serializable;

/**
 * @Description: 附件用户--（预留）
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/2/22 12:54
 * version V1.0.0
 */
public class AttachmentUser implements Serializable {
    private static final long serialVersionUID = 8477489920919595805L;

    /** 用户名 **/
    private String username;
    /** 密码 **/
    private String password;
    /** 应用 **/
    private String app;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
