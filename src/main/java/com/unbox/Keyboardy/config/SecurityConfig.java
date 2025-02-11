package com.unbox.Keyboardy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity // 웹에서 security 적용 가능
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 접근 제한
            .authorizeHttpRequests(authorize -> authorize
                // 기본 경로
                .requestMatchers("/", "/css/**", "/img/**", "/js/**", "/auth").permitAll()
                .requestMatchers("/fragments/**", "/layouts/**").permitAll()
                // user 관련 경로
                .requestMatchers("/login", "/signup", "/logout", "/member/**").permitAll()
            )
            // 로그인
            .formLogin(login -> 
                login
                    .loginPage("/login")
                    // 아이디의 id 파라미터가 다를경우
                    // .usernameParameter("userId")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            // 소셜 로그인
            .oauth2Login(oauth2Login -> 
                oauth2Login
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login")
                    .permitAll()
            )
            //로그아웃
            .logout(logout -> 
                logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
            );

        // csrf 필터 비활성화
        // http
        //     .csrf(csrf -> csrf.disable());

        // 세션
        // http.sessionManagement(session -> 
        //     session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
        // );

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
