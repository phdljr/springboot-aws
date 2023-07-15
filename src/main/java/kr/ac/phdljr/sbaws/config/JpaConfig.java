package kr.ac.phdljr.sbaws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // BaseTimeEntity 적용하기 위해 설정
public class JpaConfig {
}
