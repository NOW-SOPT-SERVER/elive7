package opt.sopt.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //jpa가 엔티티를 감시할 수 있도록
@Configuration
public class JpaAuditingConfig {
}
