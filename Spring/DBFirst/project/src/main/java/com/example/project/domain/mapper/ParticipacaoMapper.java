package com.example.project.domain.mapper;

import com.example.project.domain.dto.request.ParticipacaoCreateRequest;
import com.example.project.domain.dto.response.ParticipacaoResponse;
import com.example.project.domain.entities.Participacao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipacaoMapper {

    private final ModelMapper mapper;

    @Autowired
    public ParticipacaoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ParticipacaoResponse toDto(Participacao input) {
        return mapper.map(input, ParticipacaoResponse.class);
    }

    public Participacao fromDto(ParticipacaoCreateRequest input) {
        return mapper.map(input, Participacao.class);
    }

}