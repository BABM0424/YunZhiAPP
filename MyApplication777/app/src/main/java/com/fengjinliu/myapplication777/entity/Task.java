package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;
import java.util.Date;

public class Task {
    private BigInteger id;
    private Date start_time;
    private Date end_time;
    private BigInteger period_id;
    private String information;
    private String name;
    private String type;
    private String word_file;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public BigInteger getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(BigInteger period_id) {
        this.period_id = period_id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWord_file() {
        return word_file;
    }

    public void setWord_file(String word_file) {
        this.word_file = word_file;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", period_id=" + period_id +
                ", information='" + information + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", word_file='" + word_file + '\'' +
                '}';
    }
}
