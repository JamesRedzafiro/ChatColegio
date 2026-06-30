package com.sistemasdistribuidos.chatcolegio.dto;

import java.util.List;

import com.sistemasdistribuidos.chatcolegio.entity.Mensaje;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistorialMensajesResponse {
    private List<Mensaje> items;
    private String nextCursor; 
}