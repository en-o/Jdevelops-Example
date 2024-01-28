package com.example.jdevelopssbootauthenticationjredisdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user info
 *
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    String name;
    Integer sex;
}
