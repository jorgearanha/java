package com.example.project.domain.mapper;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import com.example.project.configuration.MapperConfig;
import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.response.EventoResponse;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Spy;

@RunWith(MockitoJUnitRunner.class)
public class EventoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private EventoMapper mapper;

    @Test
    public void should_Convert_EventoToEventoResponse() {
        Evento e = Evento
            .builder()
            .IdEvento(1)
            .categoriaEvento(CategoriaEvento.builder().IdCategoriaEvento(1).build())
            .statusEvento(StatusEvento.builder().IdEventoStatus(1).build())
            .Nome("Nome")
            .DataHoraInicio(new Date())
            .DataHoraFim(new Date())
            .Local("Local")
            .Descricao("Descricao")
            .LimiteVagas(10)
            .build();

        EventoResponse dto = mapper.toDto(e);
        dto.setIdEvento(1);

        assertEquals("Valores diferentes encontrados 1", dto.getIdEvento(), e.getIdEvento());
        assertEquals("Valores diferentes encontrados 2", dto.getCategoriaEvento(), 
            e.getCategoriaEvento());
        assertEquals("Valores diferentes encontrados 3", dto.getStatusEvento(), 
            e.getStatusEvento());
        assertEquals("Valores diferentes encontrados 4", dto.getNome(), e.getNome());
        assertEquals("Valores diferentes encontrados 5", dto.getDataHoraInicio(), e.getDataHoraInicio());
        assertEquals("Valores diferentes encontrados 6", dto.getDataHoraFim(), e.getDataHoraFim());
        assertEquals("Valores diferentes encontrados 7", dto.getLocal(), e.getLocal());
        assertEquals("Valores diferentes encontrados 8", dto.getDescricao(), e.getDescricao());
        assertEquals("Valores diferentes encontrados 9", dto.getLimiteVagas(), e.getLimiteVagas());
    }

    @Test
    public void should_Convert_EventoCreateRequestToEvento() {
        EventoCreateRequest dto = EventoCreateRequest
        .builder()
        .IdCategoriaEvento(1)
        .IdEventoStatus(1)
        .Nome("Nome")
        .DataHoraInicio(new Date())
        .DataHoraFim(new Date())
        .Local("Local")
        .Descricao("Descricao")
        .LimiteVagas(10)
        .build();

    Evento e = mapper.fromDto(dto);
    e.setCategoriaEvento(CategoriaEvento.builder().IdCategoriaEvento(1).build());
    e.setStatusEvento(StatusEvento.builder().IdEventoStatus(1).build());

    assertEquals("Valores diferentes encontrados 1", dto.getIdCategoriaEvento(),
        e.getCategoriaEvento().getIdCategoriaEvento());
    assertEquals("Valores diferentes encontrados 2", dto.getIdEventoStatus(), 
        e.getStatusEvento().getIdEventoStatus());
    assertEquals("Valores diferentes encontrados 3", dto.getNome(), e.getNome());
    assertEquals("Valores diferentes encontrados 4", dto.getDataHoraInicio(), e.getDataHoraInicio());
    assertEquals("Valores diferentes encontrados 5", dto.getDataHoraFim(), e.getDataHoraFim());
    assertEquals("Valores diferentes encontrados 6", dto.getLocal(), e.getLocal());
    assertEquals("Valores diferentes encontrados 7", dto.getDescricao(), e.getDescricao());
    assertEquals("Valores diferentes encontrados 8", dto.getLimiteVagas(), e.getLimiteVagas());
    }

}