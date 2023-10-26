package com.ajromero.webapp.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                .requestMatchers("/products").permitAll()
                //.requestMatchers(HttpMethod.POST,"/customers","/customers/**").permitAll()
                .anyRequest().authenticated());

        http.oauth2ResourceServer((oauth2)->oauth2.jwt(Customizer.withDefaults()));
        //http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }
}
