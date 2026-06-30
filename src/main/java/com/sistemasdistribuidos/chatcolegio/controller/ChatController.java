package com.sistemasdistribuidos.chatcolegio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistemasdistribuidos.chatcolegio.dto.CrearConversacionRequest;
import com.sistemasdistribuidos.chatcolegio.dto.HistorialMensajesResponse;
import com.sistemasdistribuidos.chatcolegio.entity.Conversation;
import com.sistemasdistribuidos.chatcolegio.entity.Mensaje;

@RestController
@RequestMapping("/conversations")
public class ChatController {

    /**
     * 1. GET /conversations
     * Obtiene todas las conversaciones del usuario autenticado.
     */
    @GetMapping
    public ResponseEntity<List<Conversation>> obtenerConversaciones() {
        
        String usuarioAutenticadoId = "u1"; 

        
        List<Conversation> listaSimulada = new ArrayList<>();
        
        
        return ResponseEntity.ok(listaSimulada);
    }

    /**
     * 2. GET /conversations/{id}
     * Obtiene los detalles de una sola conversación si el usuario pertenece a ella.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Conversation> obtenerConversacionPorId(@PathVariable String id) {
        
        
        Conversation conversacionSimulada = new Conversation();
        conversacionSimulada.setId(id);
        
        return ResponseEntity.ok(conversacionSimulada);
    }

    /**
     * 3. POST /conversations
     * Obtiene o crea una conversación directa (1:1) con otro usuario.
     */
    @PostMapping
    public ResponseEntity<Conversation> obtenerOCrearConversacion(@RequestBody CrearConversacionRequest request) {
        String usuarioAutenticadoId = "u1";
        String otroUsuarioId = request.getUserId();

        
        Conversation nuevaConversacion = new Conversation();
        nuevaConversacion.setId("c_nueva_123");
        
        return ResponseEntity.ok(nuevaConversacion);
    }

    /**
     * 4. GET /conversations/{id}/messages
     * Recupera el historial de mensajes de un chat de forma paginada por cursor.
     */
    @GetMapping("/{id}/messages")
    public ResponseEntity<HistorialMensajesResponse> obtenerMensajesPaginados(
            @PathVariable String id,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "30") int limit) {



        List<Mensaje> mensajesSimulados = new ArrayList<>();
        
        HistorialMensajesResponse response = HistorialMensajesResponse.builder()
                .items(mensajesSimulados)
                .nextCursor(null) 
                .build();

        return ResponseEntity.ok(response);
    }
}