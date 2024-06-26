package cn.tannn.jdevelops.demo.apis.log.apislog.console.entity;

import java.math.BigDecimal;

public class UserEntity {

    String one;
    BigDecimal number;

    public UserEntity(String one, BigDecimal number) {
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

    public UserEntity() {
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
