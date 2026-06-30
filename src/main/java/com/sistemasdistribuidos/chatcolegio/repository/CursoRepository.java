package com.sistemasdistribuidos.chatcolegio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemasdistribuidos.chatcolegio.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}