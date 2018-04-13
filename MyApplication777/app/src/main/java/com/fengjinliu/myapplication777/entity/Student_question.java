package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Student_question {
    private BigInteger id;
    private BigInteger user_id;
    private BigInteger question_id;
    private char own_answer;
    private Integer is_true;//1代表对，0代表错
    private BigInteger course_id;

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

    public BigInteger getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(BigInteger question_id) {
        this.question_id = question_id;
    }

    public char getOwn_answer() {
        return own_answer;
    }

    public void setOwn_answer(char own_answer) {
        this.own_answer = own_answer;
    }

    public Integer getIs_true() {
        return is_true;
    }

    public void setIs_true(Integer is_true) {
        this.is_true = is_true;
    }

    public BigInteger getCourse_id() {
        return course_id;
    }

    public void setCourse_id(BigInteger course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Student_question{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", question_id=" + question_id +
                ", own_answer=" + own_answer +
                ", is_true=" + is_true +
                ", course_id=" + course_id +
                '}';
    }
}
