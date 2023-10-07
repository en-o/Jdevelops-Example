package cn.tan.authentication.sas.server.entity.oauth;

import cn.tan.authentication.sas.server.entity.oauth.key.Oauth2AuthorizationConsentUPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

/**
 * 授权确认表
 * @sse https://springdoc.cn/spring-authorization-server/core-model-components.html#oauth2-authorization-consent
 * @author <a href="https://tannn.cn/">tan</a>
 * @date 2023/9/28 8:46
 */
@Entity
@Table(name = "oauth2_authorization_consent")
@Getter
@Setter
@ToString
public class Oauth2AuthorizationConsent implements Serializable {
    /**
     * 联合组键
     */
    @EmbeddedId
    private Oauth2AuthorizationConsentUPK upk;

    /**
     * 权限范围 {@Oauth2RegisteredClient#getAuthorizedScopes()}
     * 由资源所有者授予客户端的权限。一个授权可以代表一个scope、一个claim、一个许可（permission）、一个角色，以及其他。
     */
    @Comment("权限范围 {@Oauth2RegisteredClient#getAuthorizedScopes()}")
    @Column(columnDefinition = " varchar(1000)  not null")
    private String authorities;
}
