package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Class_student {
    private BigInteger id;
    private BigInteger class_id;
    private BigInteger student_id;
    private Integer grade;//课程成绩

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getClass_id() {
        return class_id;
    }

    public void setClass_id(BigInteger class_id) {
        this.class_id = class_id;
    }

    public BigInteger getStudent_id() {
        return student_id;
    }

    public void setStudent_id(BigInteger student_id) {
        this.student_id = student_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Class_student{" +
                "id=" + id +
                ", class_id=" + class_id +
                ", student_id=" + student_id +
                ", grade=" + grade +
                '}';
    }
}
