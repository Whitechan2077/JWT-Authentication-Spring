package org.jwtauth.auth;

import lombok.RequiredArgsConstructor;
import org.jwtauth.user.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public ResponseEntity<?> doLogin(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        Users user = (Users) authentication.getPrincipal();
        return ResponseEntity
                .ok(AuthResponse
                        .builder()
                        .accessToken(jwtService.generateAccessToken(user))
                        .refreshToken(jwtService.generateRefreshToken(user))
                        .build());
    }
}
