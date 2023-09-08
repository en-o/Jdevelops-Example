package com.example.jdevelopssbootauthenticationjredisdemo.bean;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 14:24
 */
@Data
public class Login {
    String username;String password;
    boolean onlyOnline; List<String> roles;java.util.List<String> permissions;
}
