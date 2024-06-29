package cn.tannn.demo.jdevelops.frameworksweb.entity;

import cn.tannn.demo.jdevelops.frameworksweb.CommonBean;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2024/6/30 上午1:25
 */
@Entity
@Table(name = "test_web")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@DynamicUpdate
@DynamicInsert
public class TestWeb extends CommonBean<TestWeb> {
    String name;
    String address;


}
