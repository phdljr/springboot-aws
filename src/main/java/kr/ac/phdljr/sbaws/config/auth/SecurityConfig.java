package kr.ac.phdljr.sbaws.config.auth;

import kr.ac.phdljr.sbaws.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // h2-console 화면을 사용하기 위해
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole("USER")
                // 설정된 값 이외의 나머지 URL은 인증된 사용자들에게만 허용(로그인한 사람)
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                /*
                oauth2Login: OAuth2 로그인 기능에 대한 여러 설정의 진입점
                userInfoEndpoint: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                userService: 소셜 로그인 성공 시 후속 소치를 진행할 UserService 인터페이스의 구현체를 등록
                            리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
                */
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
