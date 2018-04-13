package com.fengjinliu.myapplication777.entity;
import java.math.BigInteger;
import java.util.Date;

public class Trainging {
    private BigInteger id;
    private String traing_introduction;
    private Date start_time;
    private Date end_time;
    private Integer student_num;
    private BigInteger course_id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTraing_introduction() {
        return traing_introduction;
    }

    public void setTraing_introduction(String traing_introduction) {
        this.traing_introduction = traing_introduction;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Integer getStudent_num() {
        return student_num;
    }

    public void setStudent_num(Integer student_num) {
        this.student_num = student_num;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Trainging{" +
                "id=" + id +
                ", traing_introduction='" + traing_introduction + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", student_num=" + student_num +
                ", course_id=" + course_id +
                '}';
    }
}
