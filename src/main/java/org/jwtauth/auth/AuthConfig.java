package org.jwtauth.auth;

import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.jwtauth.core.exception.CustomException;
import org.jwtauth.user.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Configuration
@RestController
@Slf4j
@RequestMapping("api/v1")
public class AuthConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        return (u) -> userRepository.findByUsername(u)
                .orElseThrow(() -> {
                    log.info("Login failed: username or password not found");
                    return new CustomException(HttpStatus.UNAUTHORIZED, "Error", "Username or password not found", Map.of());
                });
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
