package com.example.project.domain.mapper;

import static org.junit.Assert.assertEquals;

import com.example.project.configuration.MapperConfig;
import com.example.project.domain.dto.request.CategoriaEventoCreateRequest;
import com.example.project.domain.dto.response.CategoriaEventoResponse;
import com.example.project.domain.entities.CategoriaEvento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Spy;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaEventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private CategoriaEventoMapper mapper;

    @Test
    public void should_Convert_CategoriaEventoToCategoriaEventoResponse() {
        CategoriaEvento ce = CategoriaEvento
            .builder()
            .idCategoriaEvento(1)
            .nomeCategoria("NomeCategoria")
            .build();
        
        CategoriaEventoResponse dto = mapper.toDto(ce);

        assertEquals("Valores diferentes encontrados", ce.getIdCategoriaEvento(), dto.getIdCategoriaEvento());
        assertEquals("Valores diferentes encontrados", ce.getNomeCategoria(), dto.getNomeCategoria());
    }

    @Test
    public void should_Convert_CategoriaEventoResponseToCategoriaEvento() {
        CategoriaEventoCreateRequest dto = CategoriaEventoCreateRequest
            .builder()
            .NomeCategoria("NomeCategoria")
            .build();

        CategoriaEvento ce = mapper.fromDto(dto);

        assertEquals("Valores diferentes encontrados", dto.getNomeCategoria(), ce.getNomeCategoria());
    }

}