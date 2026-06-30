package com.sistemasdistribuidos.chatcolegio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sistemasdistribuidos.chatcolegio.entity.Conversation;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, String> {

    
    List<Conversation> findByParticipante1IdOrParticipante2IdOrderByUltimaActividadDesc(String id1, String id2);

   
    @Query("SELECT c FROM Conversacion c WHERE " +
           "(c.participante1.id = :user1Id AND c.participante2.id = :user2Id) OR " +
           "(c.participante1.id = :user2Id AND c.participante2.id = :user1Id)")
    Optional<Conversation> findConversacionDirecta(@Param("user1Id") String user1Id, @Param("user2Id") String user2Id);
}