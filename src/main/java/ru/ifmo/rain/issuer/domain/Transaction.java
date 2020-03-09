package ru.ifmo.rain.issuer.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Transaction {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[\\d ]{16,}", message = "invalid account number")
    private String number;
    @NotNull
    private Double count;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "\\d\\d/\\d\\d(\\d\\d)?")
    private String date;
    @NotNull
    @NotEmpty
    private String ownerName;
    @NotNull
    @NotEmpty
    private String action;
    @NotNull
    @NotEmpty
    private String dateAction;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }
}
