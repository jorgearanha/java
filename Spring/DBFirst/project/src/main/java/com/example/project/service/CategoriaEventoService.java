package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.CategoriaEventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaEventoService {

    private final CategoriaEventoRepository categoriaEventoRepository;

    @Autowired
    public CategoriaEventoService(CategoriaEventoRepository categoriaEventoRepository) {
        this.categoriaEventoRepository = categoriaEventoRepository;
    }

    public List<CategoriaEvento> listClient() {
        return categoriaEventoRepository.findAll();
    }

    public CategoriaEvento findById(Integer id) {
        Optional<CategoriaEvento> categoriaEvento = categoriaEventoRepository.findById(id);
        return categoriaEvento.orElseThrow(() -> new DataNotFoundException("CategoriaEvento Not found"));
    }

}