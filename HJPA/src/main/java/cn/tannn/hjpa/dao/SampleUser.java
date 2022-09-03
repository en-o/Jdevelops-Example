package cn.tannn.hjpa.dao;

import cn.jdevelops.jap.annotation.JpaSelectOperator;
import cn.jdevelops.jap.enums.SQLConnect;
import cn.jdevelops.jap.enums.SQLOperator;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;

/**
 * @author tn
 * @version 1
 * @date 2022-03-25 15:12
 */
public interface SampleUser {


     String getUserNo();


     String getName();


    @Value("#{address + 'in' + name}")
    String getAddressAndName();
}
