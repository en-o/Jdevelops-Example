package com.example.jdevelopssbootauthenticationjwtdemo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestBean {
    String remark;

    public TestBean(String remark) {
        this.remark = remark;
    }
}
