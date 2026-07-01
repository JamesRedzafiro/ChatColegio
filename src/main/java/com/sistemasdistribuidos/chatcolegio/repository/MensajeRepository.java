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

    // Cambiado de TimestampServidor a CreatedAt
    List<Mensaje> findByHiloIdOrderByCreatedAtDesc(String hiloId, Pageable pageable);

    // Cambiado de TimestampServidor a CreatedAt
    List<Mensaje> findByHiloIdAndCreatedAtBeforeOrderByCreatedAtDesc(
            String hiloId, LocalDateTime timestamp, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Mensaje m WHERE m.hilo.id = :hiloId " +
           "AND m.remitente.id != :usuarioId AND m.status != com.sistemasdistribuidos.chatcolegio.entity.MessageStatus.READ")
    int countMensajesNoLeidos(@Param("hiloId") String hiloId, @Param("usuarioId") String usuarioId);
}