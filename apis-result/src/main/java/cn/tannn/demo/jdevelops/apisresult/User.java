package cn.tannn.demo.jdevelops.apisresult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BEAN
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2024/5/9 上午9:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    Integer age;
    Integer sex;
}
