package kr.ac.phdljr.sbaws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // BaseTimeEntity 적용하기 위해 설정
@SpringBootApplication
public class SbawsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbawsApplication.class, args);
    }

}
