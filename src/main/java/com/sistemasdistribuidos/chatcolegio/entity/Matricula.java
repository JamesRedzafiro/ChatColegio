package com.sistemasdistribuidos.chatcolegio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "matriculas")
@Data
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    //Relación con Curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    private String periodo;
}