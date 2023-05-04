package com.example.jdevelopsapisigndemo.entity;


import lombok.*;

import java.io.Serializable;

/**
 * 测试
 * @author tn
 * @version 1
 * @date 2020/11/25 13:22
 */
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity2 implements Serializable {
    private static final long serialVersionUID = 315654089784739497L;
    private String age, name, sign;
}
