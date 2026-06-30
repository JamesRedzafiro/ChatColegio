package com.sistemasdistribuidos.chatcolegio.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "conversaciones_hilos")
@Data
public class ConversacionHilo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    //Relación con el primer participante
    @ManyToOne
    @JoinColumn(name = "participante_1_id")
    private Usuario participante1;

    //Relación con el segundo participante
    @ManyToOne
    @JoinColumn(name = "participante_2_id")
    private Usuario participante2;

    @Column(name = "ultima_actividad")
    private LocalDateTime ultimaActividad;
}