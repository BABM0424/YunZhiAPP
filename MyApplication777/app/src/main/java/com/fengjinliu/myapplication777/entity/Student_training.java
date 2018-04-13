package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Student_training {
    private BigInteger id;
    private BigInteger student_id;
    private BigInteger training_id;
    private String report;
    private Integer score;

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

    public BigInteger gettraining_id() {
        return training_id;
    }

    public void settraining_id(BigInteger training_id) {
        this.training_id = training_id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student_training{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", training_id=" + training_id +
                ", report='" + report + '\'' +
                ", score=" + score +
                '}';
    }
}
