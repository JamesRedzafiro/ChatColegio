package com.sistemasdistribuidos.chatcolegio.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "conversaciones_hilos") 
@Data
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @ManyToOne
    @JoinColumn(name = "participante_1_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario participante1;

    @ManyToOne
    @JoinColumn(name = "participante_2_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario participante2;

    @Column(name = "ultima_actividad")
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoConversacion type = TipoConversacion.DIRECT;

    @Transient
    @JsonProperty("unreadCount")
    private Integer unreadCount = 0;

    @Transient
    private Mensaje lastMessage; 


    @JsonProperty("participants")
    public List<Usuario> getParticipants() {
        List<Usuario> lista = new ArrayList<>();
        if (participante1 != null) {
            lista.add(participante1);
        }
        if (participante2 != null) {
            lista.add(participante2);
        }
        return lista;
    }
}