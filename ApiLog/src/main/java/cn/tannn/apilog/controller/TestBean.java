package cn.tannn.apilog.controller;

/**
 * 测试
 *
 * @author tnnn
 * @version V1.0
 * @date 2022-08-14 01:41
 */
public class TestBean {
    String param,test;

    @Override
    public String toString() {
        return "TestBean{" +
                "param='" + param + '\'' +
                ", test='" + test + '\'' +
                '}';
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
