package com.sistemasdistribuidos.chatcolegio.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mensajes")
@Data
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "client_message_id", nullable = true)
    private String clientMessageId;

    //Relación al hilo de la conversación
    @ManyToOne
    @JoinColumn(name = "hilo_id")
    private Conversation hilo;

    //Relación al usuario que envía el mensaje
    @ManyToOne
    @JoinColumn(name = "remitente_id")
    private Usuario remitente;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "timestamp_servidor")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private MessageStatus status = MessageStatus.SENT; 

    
}