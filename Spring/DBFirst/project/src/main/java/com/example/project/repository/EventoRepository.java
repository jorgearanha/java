package com.example.project.repository;

import java.util.List;
import java.util.Date;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByStatusEvento(StatusEvento statusEvento);
    List<Evento> findByCategoriaEvento(CategoriaEvento categoriaEvento);
    
    @Query(value = "SELECT * FROM Evento e WHERE dataHoraInicio >= :date", nativeQuery = true)
    List<Evento> findPersonalizado(@Param("date") Date date);
}