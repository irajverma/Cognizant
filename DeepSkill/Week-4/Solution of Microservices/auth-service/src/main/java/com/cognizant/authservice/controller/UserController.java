package com.cognizant.authservice.controller;

import com.cognizant.authservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        // Dummy authenticate for demonstration
        if (("admin".equals(username) && "pwd".equals(password)) || ("user".equals(username) && "pwd".equals(password))) {
            String role = "admin".equals(username) ? "ROLE_ADMIN" : "ROLE_USER";
            String token = jwtTokenProvider.createToken(username, role);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/user")
    public ResponseEntity<Principal> user(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        if (jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.ok(jwtTokenProvider.getUsername(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token");
    }
}
