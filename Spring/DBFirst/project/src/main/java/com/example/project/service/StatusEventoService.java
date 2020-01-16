package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.StatusEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusEventoService {

    private final StatusEventoRepository statusEventoRepository;

    @Autowired
    public StatusEventoService(StatusEventoRepository statusEventoRepository) {
        this.statusEventoRepository = statusEventoRepository;
    }

    public List<StatusEvento> listClient() {
        return statusEventoRepository.findAll();
    }

    public StatusEvento findById(Integer id) {
        Optional<StatusEvento> statusEvento = statusEventoRepository.findById(id);
        return statusEvento.orElseThrow(() -> new DataNotFoundException("StatusEventoRepository Not found"));
    }

}