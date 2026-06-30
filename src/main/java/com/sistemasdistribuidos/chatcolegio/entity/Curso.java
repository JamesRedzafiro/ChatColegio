package com.sistemasdistribuidos.chatcolegio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cursos") 
@Data
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(name ="nombre_curso")
    private String nombreCurso;

    private String seccion;

    private String ciclo;
    
}
