package com.sistemasdistribuidos.chatcolegio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemasdistribuidos.chatcolegio.entity.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, String> {
    
    
    List<Matricula> findByCursoId(Long cursoId);
}