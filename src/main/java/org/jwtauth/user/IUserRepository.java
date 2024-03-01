package org.jwtauth.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users,String> {
    Optional<Users> findByUsername(String username);
}
