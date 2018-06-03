package com.itheima.domain;

public class Account {
    private int id;
    private double total;
    private double balance;
    private double interest;
    private int c_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", total=" + total +
                ", balance=" + balance +
                ", interest=" + interest +
                ", c_id=" + c_id +
                '}';
    }
}
