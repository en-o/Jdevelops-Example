package cn.tannn.demo.frameworksfile.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
