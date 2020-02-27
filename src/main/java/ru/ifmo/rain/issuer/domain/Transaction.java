package ru.ifmo.rain.issuer.domain;

public class Transaction {
    private String acquire;
    private String number;
    private Double count;
    private String overDate;
    private String cvv2;
    private String ownerName;

    public String getAcquire() {
        return acquire;
    }

    public void setAcquire(String acquire) {
        this.acquire = acquire;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
