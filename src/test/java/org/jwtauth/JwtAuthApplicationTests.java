package org.jwtauth;

import org.junit.jupiter.api.Test;
import org.jwtauth.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JwtAuthApplicationTests {
    @Autowired
    private IUserRepository userRepository;

    @Test
    void contextLoads() {
        userRepository.findAll().forEach((u)-> {
            u.getAuthorities().forEach(System.out::println);
        });
    }

}
