package com.example.project.repository;

import java.util.List;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByStatusEvento(StatusEvento statusEvento);
    List<Evento> findByCategoriaEvento(CategoriaEvento categoriaEvento);
    List<Evento> findByDataHoraInicioBetween(String ini,String fim);
}