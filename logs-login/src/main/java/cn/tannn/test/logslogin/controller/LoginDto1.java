package cn.tannn.test.logslogin.controller;

import cn.tannn.jdevelops.result.utils.UUIDUtils;
import lombok.Data;

/**
 * @author <a href="https://t.tannn.cn/">tnnn</a>
 * @version V1.0
 * @date 2025/4/27 22:14
 */
@Data
public class LoginDto1 {
    String id;
    String loginName;
    String password;
    String name;

    public String getId() {
        return UUIDUtils.getInstance().generateShortUuid();
    }
}
