package com.example.webdemo.controller.dto;

import java.math.BigDecimal;

public class LogUserDTO {

    String one;
    BigDecimal number;

    public LogUserDTO(String one, BigDecimal number) {
        this.one = one;
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "one='" + one + '\'' +
                ", number=" + number +
                '}';
    }

    public LogUserDTO() {
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}
