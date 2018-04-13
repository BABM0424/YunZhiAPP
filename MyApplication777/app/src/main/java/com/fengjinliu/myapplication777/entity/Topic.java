package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;
import java.util.Date;

public class Topic {
    private BigInteger id;
    private BigInteger belonging_id;
    private Date time;
    private Integer type;//0代表校友圈，1代表课程交流圈，2代表实训交流圈
    private BigInteger user_id;
    private String heading;
    private String content;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getBelonging_id() {
        return belonging_id;
    }

    public void setBelonging_id(BigInteger belonging_id) {
        this.belonging_id = belonging_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", belonging_id=" + belonging_id +
                ", time=" + time +
                ", type=" + type +
                ", user_id=" + user_id +
                ", heading='" + heading + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
