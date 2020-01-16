package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.Evento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> listClient() {
        return eventoRepository.findAll();
    }

    public Evento findById(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElseThrow(() -> new DataNotFoundException("Evento Not found"));
    }

    public Evento createEvento(Evento evento, Integer idCategoria, Integer idStatus) {
        evento.getStatusEvento().setIdEventoStatus(idStatus);
        evento.getCategoriaEvento().setIdCategoriaEvento(idCategoria);
        return eventoRepository.save(evento);
    }

}