package ru.ifmo.rain.issuer.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Account {
    @NotNull
    private long id;
    @NotNull
    @NotEmpty
    private String number;
    @NotNull
    @NotEmpty
    private Date overDate;

    @NotNull
    @NotEmpty
    private Double count;

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOverDate() {
        return overDate;
    }

    public void setOverDate(Date overDate) {
        this.overDate = overDate;
    }
}

