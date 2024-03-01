package org.jwtauth.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jwtauth.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    @Value("${application.jwt.secret_key}")
    public String secretKey;
    @Value("${application.jwt.access_token_expired}")
    public long accessTokenExpired;
    @Value("${application.jwt.refresh_token_expired}")
    public long refreshTokenExpired;

    public String generateAccessToken(Users users){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT
                .create().withSubject(users.getUsername())
                .withClaim("roles",users.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpired))
                .sign(algorithm);
    }
    
    public String generateRefreshToken(Users users){
        Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
        return JWT
                .create().withSubject(users.getUsername())
                .withClaim("roles",users.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpired))
                .sign(algorithm);
    }
}
