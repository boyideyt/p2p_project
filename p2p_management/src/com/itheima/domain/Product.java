package com.itheima.domain;

public class Product {
    private int id;
    private String proNum;
    private String proName;
    private int proLimit;
    private double annualized;
    private String releaseDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProLimit() {
        return proLimit;
    }

    public void setProLimit(int proLimit) {
        this.proLimit = proLimit;
    }

    public double getAnnualized() {
        return annualized;
    }

    public void setAnnualized(double annualized) {
        this.annualized = annualized;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", proLimit=" + proLimit +
                ", annualized=" + annualized +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
