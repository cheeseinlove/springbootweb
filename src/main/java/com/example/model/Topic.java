package com.example.model;

import java.util.Date;

public class Topic {
    private Integer tid;//主键

    private Integer tsid;//发帖的板块id

    private Integer tuid;//发帖人

    private String title;//标题

    private String content;//内容

    private Integer replycount;//回复数

    private Date publishtime;//发帖时间

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getTsid() {
        return tsid;
    }

    public void setTsid(Integer tsid) {
        this.tsid = tsid;
    }

    public Integer getTuid() {
        return tuid;
    }

    public void setTuid(Integer tuid) {
        this.tuid = tuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }
}