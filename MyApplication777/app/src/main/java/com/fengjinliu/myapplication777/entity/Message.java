package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;
import java.util.Date;

public class Message {
    private BigInteger id;
    private BigInteger user_id;
    private Integer type;
    private String content;
    private Date time;
    private Integer is_read;//0代表未读，1代表已读
    private BigInteger topic_id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIs_read() {
        return is_read;
    }

    public void setIs_read(Integer is_read) {
        this.is_read = is_read;
    }

    public BigInteger getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(BigInteger topic_id) {
        this.topic_id = topic_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", is_read=" + is_read +
                ", topic_id=" + topic_id +
                '}';
    }
}
