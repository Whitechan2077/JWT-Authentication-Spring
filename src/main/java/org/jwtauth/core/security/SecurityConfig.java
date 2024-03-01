package org.jwtauth.core.security;

import lombok.RequiredArgsConstructor;
import org.jwtauth.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    @Value("${application.api}")
    private String apiPreflix;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(apiPreflix + "/admin").hasAuthority("ADMIN")
                    .requestMatchers(apiPreflix + "/user").hasAuthority("USER")
                    .requestMatchers(apiPreflix + "/auth/login").permitAll().
                    anyRequest().permitAll();
        });
        http.sessionManagement(sc -> sc.sessionCreationPolicy(STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
