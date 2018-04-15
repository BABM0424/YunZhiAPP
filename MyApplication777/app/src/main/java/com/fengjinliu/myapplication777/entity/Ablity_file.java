package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;
import java.util.Date;

public class Ablity_file {
    private BigInteger id;
    private Date time;
    private String comment;
    private BigInteger course_id;
    private BigInteger student_id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    public BigInteger getStudent_id() {
        return student_id;
    }

    public void setStudent_id(BigInteger student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Ablity_file{" +
                "id=" + id +
                ", time=" + time +
                ", Comment='" + comment + '\'' +
                ", course_id=" + course_id +
                ", student_id=" + student_id +
                '}';
    }
}
