package com.example.project.domain.mapper;

import com.example.project.domain.dto.request.StatusEventoCreateRequest;
import com.example.project.domain.dto.response.StatusEventoResponse;
import com.example.project.domain.entities.StatusEvento;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusEventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public StatusEventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StatusEventoResponse toDto(StatusEvento input) {
        return mapper.map(input, StatusEventoResponse.class);
    }

    public StatusEvento fromDto(StatusEventoCreateRequest input) {
        return mapper.map(input, StatusEvento.class);
    }

}