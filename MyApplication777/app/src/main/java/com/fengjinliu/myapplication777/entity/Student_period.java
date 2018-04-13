package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Student_period {
    private BigInteger id;
    private BigInteger student_id;
    private BigInteger period_id;
    private Integer is_click;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getStudent_id() {
        return student_id;
    }

    public void setStudent_id(BigInteger student_id) {
        this.student_id = student_id;
    }

    public BigInteger getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(BigInteger period_id) {
        this.period_id = period_id;
    }

    public Integer getIs_click() {
        return is_click;
    }

    public void setIs_click(Integer is_click) {
        this.is_click = is_click;
    }

    @Override
    public String toString() {
        return "Student_period{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", period_id=" + period_id +
                ", is_click=" + is_click +
                '}';
    }
}
