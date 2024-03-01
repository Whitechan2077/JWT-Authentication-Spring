package org.jwtauth.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jwtauth.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    @Value("${application.jwt.secret_key}")
    private String secretKey;
    @Value("${application.jwt.access_token_expired}")
    private long accessTokenExpired;
    @Value("${application.jwt.refresh_token_expired}")
    private long refreshTokenExpired;

    public String generateAccessToken(Users users) {
        log.info("Generate access token by Algorithm.HMAC256");
        return JWT
                .create().withSubject(users.getUsername())
                .withClaim("roles", users.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpired))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));
    }

    public String generateRefreshToken(Users users) {
        log.info("Generate refresh token by Algorithm.HMAC384");
        Algorithm algorithm = Algorithm.HMAC384(secretKey.getBytes());
        return JWT
                .create().withSubject(users.getUsername())
                .withClaim("roles", users.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpired))
                .sign(Algorithm.HMAC384(secretKey.getBytes()));
    }

    public String refreshAccessToken(String refreshToken) {
        //Decode refresh token
        log.info("Refresh access token by Algorithm.HMAC256");
        DecodedJWT decodedRefreshToken =
                JWT
                .require(Algorithm.HMAC384(secretKey.getBytes()))
                .build()
                .verify(refreshToken);

        //Generate Access token from refreshToken

        return JWT.create()
                .withSubject(decodedRefreshToken.getSubject())
                .withClaim("roles", decodedRefreshToken.getClaim("roles").asList(String.class))
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpired))
                .sign(Algorithm.HMAC256(secretKey.getBytes()));
    }
}
