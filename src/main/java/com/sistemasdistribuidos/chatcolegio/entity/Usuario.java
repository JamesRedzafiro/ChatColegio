package com.sistemasdistribuidos.chatcolegio.entity;

import java.time.LocalDateTime; // Actualizado a la API de fechas moderna

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuarios") 
@Data
public class Usuario { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_estudiante") 
    private Long codigoEstudiante; 

    private String nombres;
    
    private String apellidos;

    private String avatarUrl;

    @Enumerated(EnumType.STRING) 
    private RolUsuario rol; 

    @Enumerated(EnumType.STRING) 
    private MessageStatus status; 

    private LocalDateTime lastSeenAt; 
}