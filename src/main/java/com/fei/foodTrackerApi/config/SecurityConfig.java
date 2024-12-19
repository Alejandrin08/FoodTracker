package com.fei.foodTrackerApi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/account/**", "/api").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/client/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/restaurant/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/restaurant/{categoryName}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/client/**").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.PUT, "/api/client/**").hasRole("CLIENT")
                                .requestMatchers("/api/restaurant/**").hasRole("OWNER")
                                .requestMatchers(HttpMethod.GET, "/api/menu/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/menu/").hasRole("OWNER")
                                .requestMatchers(HttpMethod.PUT,"/api/menu/{dish}").hasRole("OWNER")
                                .requestMatchers(HttpMethod.DELETE,"/api/menu/{dish}").hasRole("OWNER")
                                .requestMatchers(HttpMethod.POST, "/api/rating/{restaurantName}").hasRole("CLIENT")
                                .requestMatchers(HttpMethod.GET,"/api/rating/{restaurantName}").permitAll()
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
