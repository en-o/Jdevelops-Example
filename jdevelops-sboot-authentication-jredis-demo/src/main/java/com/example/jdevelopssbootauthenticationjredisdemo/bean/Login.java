package com.example.jdevelopssbootauthenticationjredisdemo.bean;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/8 14:24
 */
@Data
public class Login {

    String username;

    String password;

    /**
     * 禁用[账号禁用，跟锁定有点像，但是这个是没有失效，除非管理员主动解除], 同类状态有：
     * - 删除
     * - 停用
     */
    private boolean disabledAccount;

    /**
     * 锁定[过度的尝试，判断未异常操作进行账号锁定，一般是有时效的]
     */
    private boolean excessiveAttempts;

    /**
     * 以前的是否会被挤下线
     */
    boolean onlyOnline;


    List<String> roles;

    List<String> permissions;
}
