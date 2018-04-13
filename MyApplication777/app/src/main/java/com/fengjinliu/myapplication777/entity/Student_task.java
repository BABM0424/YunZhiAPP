package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Student_task {
    private BigInteger id;
    private BigInteger student_id;
    private BigInteger task_id;
    private Integer grade;

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

    public BigInteger getTask_id() {
        return task_id;
    }

    public void setTask_id(BigInteger task_id) {
        this.task_id = task_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student_task{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", task_id=" + task_id +
                ", grade=" + grade +
                '}';
    }
}
