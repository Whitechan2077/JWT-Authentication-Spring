package org.jwtauth.data;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${application.api}")
public class DataController {
    @GetMapping("/admin")
    public ResponseEntity<?> dataAdmin(){
        return ResponseEntity.ok("Authorization Admin");
    }
    @GetMapping("/user")
    public ResponseEntity<?> dataUser(){
        return ResponseEntity.ok("Authorization User");
    }
}
