package org.jwtauth.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jwtauth.user.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public ResponseEntity<?> doLogin(AuthDTO.AuthRequest authRequest){
        log.info("Login starting ....");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        Users user = (Users) authentication.getPrincipal();
        log.info("Login complete ....");
        return ResponseEntity
                .ok(AuthDTO.AuthResponse
                        .builder()
                        .accessToken(jwtService.generateAccessToken(user))
                        .refreshToken(jwtService.generateRefreshToken(user))
                        .build());
    }
}
