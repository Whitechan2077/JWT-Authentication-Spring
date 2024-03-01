package org.jwtauth.user;

import lombok.*;

public abstract class RoleDTO {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class RoleResponseDTO{
        private String roleName;
    }
}
