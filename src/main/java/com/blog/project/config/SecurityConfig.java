package com.blog.project.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

/* 스프링 시큐리티 세트(3가지) */
@Configuration             // 빈 등록(IoC 관리)
@EnableWebSecurity         // 시큐리티 필터가 등록됨 (= 스프링 시큐리티 활성화 +  "추가 설정"을 해당 파일에서 하겠다)
@EnableMethodSecurity      // 특정 주소로 접근하면, 권한 및 인증을 "미리 체크"하겠다는 뜻
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.csrf(AbstractHttpConfigurer::disable);      // csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
        http.authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/auth/**")).permitAll()     // 이 경로로 들어오는 요청은 // 모두에게 다 허용해주겠다 (=인증이 필요 없음)
                        .requestMatchers(mvcMatcherBuilder.pattern("/js/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/css/**")).permitAll()
                        .requestMatchers(mvcMatcherBuilder.pattern("/image/**")).permitAll()
                        .anyRequest()                            // 위 경로를 제외한 다른 모든 요청은
                        .authenticated()                         // 인증이 되어야 함   -> 필터링
                )
                .formLogin(login -> login                        // form 방식 로그인 사용
                        .loginPage("/auth/loginForm"));            // 커스텀 한 로그인 페이지 경로에 사용자를 리디렉션하도록 설정

        return http.build();
    }
}

/* 스프링 시큐리티 세트(3가지) /*

@Configuration         // 빈 등록(IoC 관리)
@EnableWebSecurity     // 시큐리티 필터가 등록됨 (= 스프링 시큐리티 활성화 +  "추가 설정"을 해당 파일에서 하겠다)
@EnableGlobalMethodSecurity(prePostEnabled = true)      // 특정 주소로 접근하면, 권한 및 인증을 "미리 체크"하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**")       // 이 경로로 들어오는 요청은
                .permitAll()                   // 모두에게 다 허용해주겠다 (=인증이 필요 없음)
                .anyRequest()                  // 위 경로를 제외한 다른 모든 요청은
                .authenticated()               // 인증이 되어야 함   -> 필터링
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");
    }
}*/
