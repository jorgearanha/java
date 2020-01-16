package com.example.project.domain.mapper;

import com.example.project.domain.dto.request.CategoriaEventoCreateRequest;
import com.example.project.domain.dto.response.CategoriaEventoResponse;
import com.example.project.domain.entities.CategoriaEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaEventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public CategoriaEventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CategoriaEventoResponse toDto(CategoriaEvento input) {
        return mapper.map(input, CategoriaEventoResponse.class);
    }

    public CategoriaEvento fromDto(CategoriaEventoCreateRequest input) {
        return mapper.map(input, CategoriaEvento.class);
    }

}