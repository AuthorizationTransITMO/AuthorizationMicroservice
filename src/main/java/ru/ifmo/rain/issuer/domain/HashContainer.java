package ru.ifmo.rain.issuer.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HashContainer {
    @NotNull
    private Long id;
    @NotNull
    @NotEmpty
    private String Hash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }
}
