package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Course {
    private BigInteger id;
    private String name;
    private String introduction;
    private String teaching_progress;
    private String knowledge_point;
    private BigInteger teacher_id;
    private String picture;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTeaching_progress() {
        return teaching_progress;
    }

    public void setTeaching_progress(String teaching_progress) {
        this.teaching_progress = teaching_progress;
    }

    public String getKnowledge_point() {
        return knowledge_point;
    }

    public void setKnowledge_point(String knowledge_point) {
        this.knowledge_point = knowledge_point;
    }

    public BigInteger getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(BigInteger teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", teaching_progress='" + teaching_progress + '\'' +
                ", knowledge_point='" + knowledge_point + '\'' +
                ", teacher_id=" + teacher_id +
                ", picture='" + picture + '\'' +
                '}';
    }
}
