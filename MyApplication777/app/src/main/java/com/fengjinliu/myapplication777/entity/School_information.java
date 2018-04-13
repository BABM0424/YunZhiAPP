package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class School_information {
    private BigInteger id;
    private BigInteger school_id;
    private String content;
    private Integer type;
    private BigInteger course_id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSchool_id() {
        return school_id;
    }

    public void setSchool_id(BigInteger school_id) {
        this.school_id = school_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "School_information{" +
                "id=" + id +
                ", school_id=" + school_id +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", course_id=" + course_id +
                '}';
    }
}
