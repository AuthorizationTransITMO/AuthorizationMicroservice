package ru.ifmo.rain.issuer.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Transaction {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "(\\d ){16,}", message = "invalid account number")
    private String number;
    @NotNull
    private Double count;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d\\d/\\d\\d")
    private String overDate;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 3)
    private String cvv2;
    @NotNull
    @NotEmpty
    private String ownerName;
    @NotNull
    @NotEmpty
    private String action;


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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
