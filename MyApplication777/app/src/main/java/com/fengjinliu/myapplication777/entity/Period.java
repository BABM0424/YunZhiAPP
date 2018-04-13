package com.fengjinliu.myapplication777.entity;

import java.math.BigInteger;

public class Period {
    private BigInteger id;
    private BigInteger class_id;
    private String ppt;
    private String knowledge_point;
    private String video;

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

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public String getKnowledge_point() {
        return knowledge_point;
    }

    public void setKnowledge_point(String knowledge_point) {
        this.knowledge_point = knowledge_point;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", class_id=" + class_id +
                ", ppt='" + ppt + '\'' +
                ", knowledge_point='" + knowledge_point + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
