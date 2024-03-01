package org.jwtauth.user;

import lombok.*;

import java.util.Set;

public abstract class UserDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserResponseDTO{
        private String username;
        Set<RoleDTO.RoleResponseDTO> roles;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class UserRegisterDTO{
        private String username;
        private String password;
    }
}
