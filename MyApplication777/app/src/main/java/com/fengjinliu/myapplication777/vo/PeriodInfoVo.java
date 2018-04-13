package com.fengjinliu.myapplication777.vo;

import java.math.BigInteger;

/**
 * 作者陈滨  用于courseservice
 */
public class PeriodInfoVo {
    private BigInteger period_id;
    private String content;
    private boolean is_click;

    public BigInteger getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(BigInteger period_id) {
        this.period_id = period_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIs_click() {
        return is_click;
    }

    public void setIs_click(boolean is_click) {
        this.is_click = is_click;
    }

    @Override
    public String toString() {
        return "PeriodInfoVo{" +
                "period_id=" + period_id +
                ", content='" + content + '\'' +
                ", is_click=" + is_click +
                '}';
    }
}
