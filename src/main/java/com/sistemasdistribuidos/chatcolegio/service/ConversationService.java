package com.sistemasdistribuidos.chatcolegio.service;

import com.sistemasdistribuidos.chatcolegio.entity.Conversation; // Ajustado a Conversation
import com.sistemasdistribuidos.chatcolegio.entity.Mensaje;
import com.sistemasdistribuidos.chatcolegio.entity.Usuario;
import com.sistemasdistribuidos.chatcolegio.repository.ConversationRepository; // Ajustado a ConversationRepository
import com.sistemasdistribuidos.chatcolegio.repository.MensajeRepository;
import com.sistemasdistribuidos.chatcolegio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConversationService { // Nombre de la clase corregido

    private final ConversationRepository conversationRepository;
    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene todos los chats de un usuario y llena los datos dinámicos (@Transient)
     */
    public List<Conversation> listarConversacionesDelUsuario(String usuarioId) {
        // Buscar los hilos donde participe el usuario
        List<Conversation> conversaciones = conversationRepository
                .findByParticipante1IdOrParticipante2IdOrderByUltimaActividadDesc(usuarioId, usuarioId);

        for (Conversation conv : conversaciones) {
            // Calcular mensajes no leídos
            int noLeidos = mensajeRepository.countMensajesNoLeidos(conv.getId(), usuarioId);
            conv.setUnreadCount(noLeidos);

            // Obtener el último mensaje enviado en este hilo
            List<Mensaje> ultimosMensajes = mensajeRepository
                    .findByHiloIdOrderByTimestampServidorDesc(conv.getId(), PageRequest.of(0, 1));
            
            if (!ultimosMensajes.isEmpty()) {
                conv.setUltimoMensaje(ultimosMensajes.get(0));
            }
        }

        return conversaciones;
    }

    /**
     * Lógica "Get or Create" para chats 1:1
     */
    public Conversation obtenerOCrearConversacionDirecta(String remitenteId, String destinatarioId) {
        // 1. Verificar si ya existe el chat entre ambos
        Optional<Conversation> conversacionExistente = conversationRepository
                .findConversacionDirecta(remitenteId, destinatarioId);

        if (conversacionExistente.isPresent()) {
            return conversacionExistente.get();
        }

        // 2. Si no existe, buscar los objetos Usuario reales en la BD
        Usuario participante1 = usuarioRepository.findById(remitenteId)
                .orElseThrow(() -> new RuntimeException("Usuario remitente no encontrado"));
        Usuario participante2 = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new RuntimeException("Usuario destinatario no encontrado"));

        // 3. Crear y guardar la nueva conversación
        Conversation nuevaConversacion = new Conversation();
        nuevaConversacion.setParticipante1(participante1);
        nuevaConversacion.setParticipante2(participante2);
        nuevaConversacion.setUltimaActividad(LocalDateTime.now());
        
        return conversationRepository.save(nuevaConversacion);
    }

    public Optional<Conversation> buscarPorId(String id) {
        return conversationRepository.findById(id);
    }
}