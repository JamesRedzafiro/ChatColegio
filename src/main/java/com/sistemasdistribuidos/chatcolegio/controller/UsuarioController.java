package com.sistemasdistribuidos.chatcolegio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistemasdistribuidos.chatcolegio.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @GetMapping
    public ResponseEntity<List<UserDto>> buscarUsuarios(@RequestParam("search") String query) {
        
        
        List<UserDto> resultadosSimulados = new ArrayList<>();
        resultadosSimulados.add(new UserDto("u3", "Carla", null));
        
        return ResponseEntity.ok(resultadosSimulados);
    }
}