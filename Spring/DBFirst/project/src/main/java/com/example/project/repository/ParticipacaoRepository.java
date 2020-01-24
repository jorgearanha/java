package com.example.project.repository;

import java.util.List;

import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {
    List<Participacao> findByEvento(Evento evento);
}