package cn.tannn.jdevelopssbootjpademo.config;

import cn.jdevelops.sboot.jpa.core.auditor.AuditorNameService;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author tan
 */
@Component
public class TokenAuditorNameServiceImpl implements AuditorNameService {
    @Override
    public Optional<String> settingAuditorName() {
        return Optional.of("tan");
    }
}
