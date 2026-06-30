package com.sistemasdistribuidos.chatcolegio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn; 
    private UserDto user;
}