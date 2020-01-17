package com.example.project.domain.mapper;

import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.request.EventoUpdateRequest;
import com.example.project.domain.dto.response.EventoResponse;
import com.example.project.domain.entities.Evento;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    private final ModelMapper mapper;

    @Autowired
    public EventoMapper(ModelMapper mapper) {
        this.mapper = mapper;
        //this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        this.mapper.getConfiguration().setSkipNullEnabled(true);
    }

    public EventoResponse toDto(Evento input) {
        return mapper.map(input, EventoResponse.class);
    }

    public Evento fromDto(EventoCreateRequest input) {
        return mapper.map(input, Evento.class);
    }

    public Evento updateFromDto(EventoUpdateRequest input) {
        return mapper.map(input, Evento.class);
    }

}