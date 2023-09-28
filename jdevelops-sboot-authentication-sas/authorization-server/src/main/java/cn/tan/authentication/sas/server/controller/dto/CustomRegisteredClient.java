package cn.tan.authentication.sas.server.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Set;

/**
 * 客户端注册
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 14:31
 */
@Getter
@Setter
@ToString
public class CustomRegisteredClient {

    /**
     * 客户端ID
     */
    @NotBlank
    private String clientId;
    /**
     * 客户端Secret
     */
    @NotBlank
    private String clientSecret;

    /**
     * 客户端名称
     */
    @NotBlank
    private String clientName;

    /**
     * 客户端回调地址
     */
    @NotEmpty
    private Set<String> redirectUris;

}
