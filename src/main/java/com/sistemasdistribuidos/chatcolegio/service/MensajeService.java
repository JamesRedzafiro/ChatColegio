package com.sistemasdistribuidos.chatcolegio.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistemasdistribuidos.chatcolegio.dto.HistorialMensajesResponse;
import com.sistemasdistribuidos.chatcolegio.entity.Mensaje;
import com.sistemasdistribuidos.chatcolegio.repository.MensajeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public HistorialMensajesResponse obtenerHistorialPaginado(String hiloId, String cursor, int limit) {
            Pageable limiteRegistros = PageRequest.of(0, limit);
            List<Mensaje> mensajes;

            if (cursor == null || cursor.isBlank()) {
                
                mensajes = mensajeRepository.findByHiloIdOrderByCreatedAtDesc(hiloId, limiteRegistros);
            } else {
                LocalDateTime fechaCursor = LocalDateTime.parse(cursor, DateTimeFormatter.ISO_DATE_TIME);
                
                mensajes = mensajeRepository.findByHiloIdAndCreatedAtBeforeOrderByCreatedAtDesc(
                        hiloId, fechaCursor, limiteRegistros);
            }

            String siguienteCursor = null;
            if (!mensajes.isEmpty() && mensajes.size() == limit) {
                Mensaje ultimoMensajeDeLaPagina = mensajes.get(mensajes.size() - 1);
                siguienteCursor = ultimoMensajeDeLaPagina.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME);
            }

            Collections.reverse(mensajes);

            return HistorialMensajesResponse.builder()
                    .items(mensajes)
                    .nextCursor(siguienteCursor)
                    .build();
    }

    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }
}