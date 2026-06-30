package com.sistemasdistribuidos.chatcolegio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemasdistribuidos.chatcolegio.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    List<Usuario> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCase(String nombres, String apellidos);
}