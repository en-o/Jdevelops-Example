package com.example.jdevelopsdataddssdemo.controller.dto;


import cn.jdevelops.data.ddss.annotation.DbName;

/**
 * 测试
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/15 14:54
 */
public class TestVO {

    @DbName
    String dbName;
    String hi;


    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }
}
