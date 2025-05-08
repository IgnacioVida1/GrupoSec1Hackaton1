package com.example.hackaton1.Controllers;

import com.example.hackaton1.DTOs.Jwt.JwtAuthenticationResponse;
import com.example.hackaton1.DTOs.Jwt.SigninRequest;
import com.example.hackaton1.User.Domain.AuthenticationService;
import com.example.hackaton1.User.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}