package com.example.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = -1L;
/*
id
 */
    private Integer uid;
/*
用户名
 */
    private String uname;
/*
注册时间
 */
    private Date uregtime;
/*
用户密码
 */
    private String upassword;
/*
用户性别
 */
    private Integer usex;
/*
用户头像
 */
    private String uhead;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public Date getUregtime() {
        return uregtime;
    }

    public void setUregtime(Date uregtime) {
        this.uregtime = uregtime;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword == null ? null : upassword.trim();
    }

    public Integer getUsex() {
        return usex;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public String getUhead() {
        return uhead;
    }

    public void setUhead(String uhead) {
        this.uhead = uhead == null ? null : uhead.trim();
    }
}