package com.sistemasdistribuidos.chatcolegio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemasdistribuidos.chatcolegio.dto.AuthResponse;
import com.sistemasdistribuidos.chatcolegio.dto.LoginRequest;
import com.sistemasdistribuidos.chatcolegio.dto.RefreshRequest;
import com.sistemasdistribuidos.chatcolegio.dto.UserDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        // Simulación temporal
        UserDto usuarioSimulado = new UserDto("u1", "Ana", null);
        
        AuthResponse response = AuthResponse.builder()
                .accessToken("jwt-access-token")
                .refreshToken("refresh-token")
                .expiresIn(900) 
                .user(usuarioSimulado)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest request) {

        UserDto usuarioSimulado = new UserDto("u1", "Ana", null);
        
        AuthResponse response = AuthResponse.builder()
                .accessToken("nuevo-jwt-access-token-generado")
                .refreshToken(request.getRefreshToken()) 
                .expiresIn(900)
                .user(usuarioSimulado)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {

        return ResponseEntity.noContent().build();
    }
}