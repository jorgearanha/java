package com.example.project.domain.mapper;

import static org.junit.Assert.assertEquals;

import com.example.project.configuration.MapperConfig;
import com.example.project.domain.dto.request.StatusEventoCreateRequest;
import com.example.project.domain.dto.response.StatusEventoResponse;
import com.example.project.domain.entities.StatusEvento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Spy;

@RunWith(MockitoJUnitRunner.class)
public class StatusEventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private StatusEventoMapper mapper;

    @Test
    public void should_Convert_StatusEventoToStatusEventoResponse() {
        StatusEvento se = StatusEvento
            .builder()
            .IdEventoStatus(1)
            .NomeStatus("NomeStatus")
            .build();

        StatusEventoResponse dto = mapper.toDto(se);

        assertEquals("Valores diferentes encontrados", dto.getIdEventoStatus(), se.getIdEventoStatus());
        assertEquals("Valores diferentes encontrados", dto.getNomeStatus(), se.getNomeStatus());
    }

    @Test
    public void should_Convert_CategoriaEventoResponseToCategoriaEvento() {
        StatusEventoCreateRequest dto = StatusEventoCreateRequest
            .builder()
            .NomeStatus("NomeStatus")
            .build();


        StatusEvento se = mapper.fromDto(dto);

        assertEquals("Valores diferentes encontrados", dto.getNomeStatus(), se.getNomeStatus());
    }

}