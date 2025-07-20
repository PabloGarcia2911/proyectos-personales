package com.GreenSolar.cl.GreenSolar.controller;

import com.GreenSolar.cl.GreenSolar.auth.AuthRequest;
import com.GreenSolar.cl.GreenSolar.auth.AuthResponse;
import com.GreenSolar.cl.GreenSolar.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
