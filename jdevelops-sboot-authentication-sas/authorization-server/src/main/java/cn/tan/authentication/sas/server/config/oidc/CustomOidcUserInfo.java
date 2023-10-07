package cn.tan.authentication.sas.server.config.oidc;

import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 自定义 userinfo
 */
public class CustomOidcUserInfo extends OidcUserInfo {
    private static final long serialVersionUID = 610L;
    private final Map<String, Object> claims;

    public CustomOidcUserInfo(Map<String, Object> claims) {
        super(claims);
        Assert.notEmpty(claims, "claims cannot be empty");
        this.claims = Collections.unmodifiableMap(new LinkedHashMap(claims));
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.claims;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            CustomOidcUserInfo that = (CustomOidcUserInfo)obj;
            return this.getClaims().equals(that.getClaims());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getClaims().hashCode();
    }



    public static CustomOidcUserInfo.Builder customBuilder() {
        return new CustomOidcUserInfo.Builder();
    }

    /**
     * {@link  OidcScopes}
     */
    public static final class Builder {
        private final Map<String, Object> claims = new LinkedHashMap();

        private Builder() {
        }

        public CustomOidcUserInfo.Builder claim(String name, Object value) {
            this.claims.put(name, value);
            return this;
        }

        public CustomOidcUserInfo.Builder claims(Consumer<Map<String, Object>> claimsConsumer) {
            claimsConsumer.accept(this.claims);
            return this;
        }

        public CustomOidcUserInfo.Builder username(String username) {
            return this.claim("username", username);
        }

        public CustomOidcUserInfo.Builder nickname(String nickname) {
            return this.claim("nickname", nickname);
        }

        public CustomOidcUserInfo.Builder description(String description) {
            return this.claim("description", description);
        }

        public CustomOidcUserInfo.Builder status(Integer status) {
            return this.claim("status", status);
        }

        public CustomOidcUserInfo.Builder phoneNumber(String phoneNumber) {
            return this.claim("phone_number", phoneNumber);
        }

        public CustomOidcUserInfo.Builder email(String email) {
            return this.claim("email", email);
        }

        public CustomOidcUserInfo.Builder profile(String profile) {
            return this.claim("profile", profile);
        }

        public CustomOidcUserInfo.Builder address(String address) {
            return this.claim("address", address);
        }

        public CustomOidcUserInfo build() {
            return new CustomOidcUserInfo(this.claims);
        }

    }
}
