package org.jwtauth.role;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jwtauth.core.AbstractEntity;
import org.jwtauth.user.Users;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role extends AbstractEntity {
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;
}
