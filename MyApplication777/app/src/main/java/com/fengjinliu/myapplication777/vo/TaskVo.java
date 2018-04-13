package com.fengjinliu.myapplication777.vo;

import java.math.BigInteger;

public class TaskVo {
    private BigInteger task_id;
    private String content;

    public BigInteger getTask_id() {
        return task_id;
    }

    public void setTask_id(BigInteger task_id) {
        this.task_id = task_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TaskVo{" +
                "task_id=" + task_id +
                ", content='" + content + '\'' +
                '}';
    }
}
