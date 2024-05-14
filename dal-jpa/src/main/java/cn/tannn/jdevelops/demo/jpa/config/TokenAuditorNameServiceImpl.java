package cn.tannn.jdevelops.demo.jpa.config;

import cn.tannn.jdevelops.jpa.auditor.AuditorNameService;
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
