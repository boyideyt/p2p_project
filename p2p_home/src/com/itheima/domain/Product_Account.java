package com.itheima.domain;

import java.sql.Date;

public class Product_Account {
    private int id;
    private String pa_num;
    private Date pa_date;
    private int c_id;
    private int p_id;
    private double money;
    private double interest;
    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPa_num() {
        return pa_num;
    }

    public void setPa_num(String pa_num) {
        this.pa_num = pa_num;
    }

    public Date getPa_date() {
        return pa_date;
    }

    public void setPa_date(Date pa_date) {
        this.pa_date = pa_date;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
