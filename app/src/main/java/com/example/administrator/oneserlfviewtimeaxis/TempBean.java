package com.example.administrator.oneserlfviewtimeaxis;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TempBean {
    private String date;
    private String money;

    public TempBean(String date, String money) {
        this.date = date;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
