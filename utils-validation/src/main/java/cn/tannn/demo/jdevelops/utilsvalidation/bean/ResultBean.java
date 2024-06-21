package cn.tannn.demo.jdevelops.utilsvalidation.bean;

import cn.tannn.jdevelops.utils.desensitized.annotation.Cover;
import cn.tannn.jdevelops.utils.desensitized.enums.CoverRuleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author tnnn
 * @version V1.0
 * @date 2022-12-05 14:47
 */
@Getter
@Setter
@ToString
public class ResultBean {

    /**
     * 手机号
     */
    @Cover(rule = CoverRuleEnum.MOBILE_PHONE)
    String iphone;


    /**
     * 手机号
     */
    @Cover(rule = CoverRuleEnum.PASSWORD)
    String iphone2;


    /**
     * 固定电话
     */
    @Cover(rule = CoverRuleEnum.FIXED_PHONE)
    String fphone;

    /**
     * 身份证
     */
    @Cover(rule = CoverRuleEnum.ID_CARD)
    String idCard;

    /**
     * 中文姓名
     */
    @Cover(rule = CoverRuleEnum.CHINESE_NAME)
    String cname;


    /**
     * 地址
     */
    @Cover(rule = CoverRuleEnum.ADDRESS)
    String address;

    /**
     * 密码
     */
    @Cover(rule = CoverRuleEnum.PASSWORD)
    String password;

    /**
     * 邮件
     */
    @Cover(rule = CoverRuleEnum.EMAIL)
    String email;


    public ResultBean() {
        this.iphone = "13321285210";
        this.fphone = "03168228737";
        this.idCard = "200220129212056022";
        this.cname = "谭宁";
        this.address = "成都市金牛区二环路北三段";
        this.password = "123456";
        this.email = "c66@163.com";
    }

    public ResultBean(String iphone, String fphone, String idCard, String cname, String address, String password, String email) {
        this.iphone = iphone;
        this.fphone = fphone;
        this.idCard = idCard;
        this.cname = cname;
        this.address = address;
        this.password = password;
        this.email = email;
    }
}
