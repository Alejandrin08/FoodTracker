package com.fei.solveithomeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/api/account/**").permitAll()
                        .requestMatchers("/api/private/**").authenticated()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        );
        http.formLogin((form) -> form
                .loginPage("/login")
                .permitAll()
        );
        http.logout(LogoutConfigurer::permitAll);
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
