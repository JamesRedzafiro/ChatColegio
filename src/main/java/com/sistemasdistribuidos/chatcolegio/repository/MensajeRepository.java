package com.sistemasdistribuidos.chatcolegio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistemasdistribuidos.chatcolegio.entity.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, String> {

    // 1. Para la primera página de mensajes (Trae los últimos 'X' mensajes)
    List<Mensaje> findByHiloIdOrderByTimestampServidorDesc(String hiloId, Pageable pageable);

    // 2. Para la paginación por cursor (Trae los mensajes anteriores al timestamp del 'cursor')
    List<Mensaje> findByHiloIdAndTimestampServidorBeforeOrderByTimestampServidorDesc(
            String hiloId, LocalDateTime timestamp, Pageable pageable);

    // 3. Cuenta los mensajes no leídos en un chat donde el remitente NO sea el usuario actual
    @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.hilo.id = :hiloId " +
           "AND m.remitente.id != :usuarioId AND m.estado != com.sistemasdistribuidos.chatcolegio.entity.MessageStatus.READ")
    int countMensajesNoLeidos(@Param("hiloId") String hiloId, @Param("usuarioId") String usuarioId);
}