package cn.tannn.validations;

import com.detabes.validation.idcard.IdCard;
import com.detabes.validation.mobile.Mobile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author tnnn
 * @version V1.0
 * @date 2022-12-05 14:47
 */
@Getter
@Setter
@ToString
public class UserBean {

    @Mobile
    String iphone;

    @IdCard
    String idCard;

    public UserBean(String iphone, String idCard) {
        this.iphone = iphone;
        this.idCard = idCard;
    }

    public UserBean() {
    }
}
