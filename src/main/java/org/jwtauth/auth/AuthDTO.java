package org.jwtauth.auth;

import lombok.*;


public abstract class AuthDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
   public static class AuthRequest{
       private String username;
       private String password;
   }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
   public static class AuthResponse{
       private String accessToken;
       private String refreshToken;
   }
}
