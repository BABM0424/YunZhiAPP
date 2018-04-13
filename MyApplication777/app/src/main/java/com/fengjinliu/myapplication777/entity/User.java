package com.fengjinliu.myapplication777.entity;


import java.math.BigInteger;

public class User {
    private BigInteger id;
    private String account;
    private String password;
    private Integer identification;//1代表老师，2代表学生，3代表教务管理员
    private String name;
    private BigInteger school_id;//School school
    private String number;
    private Integer sex;//0代表男，1代表女
    private String introduction;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdentified() {
        return identification;
    }

    public void setIdentified(Integer identified) {
        this.identification = identified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getSchool_id() {
        return school_id;
    }

    public void setSchool_id(BigInteger school_id) {
        this.school_id = school_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", identification=" + identification +
                ", name='" + name + '\'' +
                ", school_id=" + school_id +
                ", number='" + number + '\'' +
                ", sex=" + sex +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
