package cn.tannn.demo.jdevelops.utilsvalidation.bean;

import cn.tannn.jdevelops.utils.validation.account.Account;
import cn.tannn.jdevelops.utils.validation.cname.Cname;
import cn.tannn.jdevelops.utils.validation.datetime.DateTime;
import cn.tannn.jdevelops.utils.validation.idcard.IdCard;
import cn.tannn.jdevelops.utils.validation.mobile.Mobile;
import cn.tannn.jdevelops.utils.validation.password.Password;
import lombok.*;


/**
 * @author tnnn
 * @version V1.0
 * @date 2022-12-05 14:47
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBean {

    /**
     * 手机号
     */
    @Mobile
    String iphone;

    /**
     * 身份证
     */
    @IdCard
    String idCard;

    /**
     * 中文姓名
     */
    @Cname
    String cname;


    /**
     * 时间yyyy-MM-dd HH:mm:ss
     */
    @DateTime
    String dateTime;

    /**
     * 密码
     */
    @Password
    String password;


    /**
     * 账户名
     */
    @Account
    String account;


}
