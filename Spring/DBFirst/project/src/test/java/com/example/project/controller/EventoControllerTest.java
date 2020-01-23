package com.example.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.response.EventoResponse;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.domain.mapper.EventoMapper;
import com.example.project.service.CategoriaEventoService;
import com.example.project.service.EventoService;
import com.example.project.service.StatusEventoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RunWith(MockitoJUnitRunner.class)
public class EventoControllerTest {
    
    @Mock
    EventoMapper mapper;

    @Mock
    EventoService service;

    @Mock
    CategoriaEventoService serviceCategoria;

    @Mock
    StatusEventoService serviceStatus;

    @InjectMocks
    EventoController controller;

    CategoriaEvento categoria = CategoriaEvento.builder() //
        .IdCategoriaEvento(1) //
        .NomeCategoria("NomeCategoria") //
        .build();

    StatusEvento status = StatusEvento.builder() //
        .IdEventoStatus(1) //
        .NomeStatus("NomeStatus") //
        .build();

    Evento entity = Evento.builder() //
        .IdEvento(1) //
        .categoriaEvento(categoria) //
        .statusEvento(status) //
        .Nome("Nome") //
        .DataHoraInicio(new Date()) //
        .DataHoraFim(new Date()) //
        .Local("Local") //
        .Descricao("Descricao") //
        .LimiteVagas(10) //
        .build();

    EventoResponse responseDto = EventoResponse.builder() //
        .IdEvento(1) //
        .categoriaEvento(categoria) //
        .statusEvento(status) //
        .Nome("Nome") //
        .DataHoraInicio(new Date()) //
        .DataHoraFim(new Date()) //
        .Local("Local") //
        .Descricao("Descricao") //
        .LimiteVagas(10) //
        .build();
    
    EventoCreateRequest dto = EventoCreateRequest.builder() //
        .IdCategoriaEvento(categoria.getIdCategoriaEvento()) //
        .IdEventoStatus(status.getIdEventoStatus()) //
        .Nome("Nome") //
        .DataHoraInicio(new Date()) //
        .DataHoraFim(new Date()) //
        .Local("Local") //
        .Descricao("Descricao") //
        .LimiteVagas(10) //
        .build();

    @Test
    public void should_getById() {
        when(service.findById(anyInt())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(responseDto);

        ResponseEntity<EventoResponse> response = controller.getById(anyInt());

        assertEquals("Status deve ser Ok/200", response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_list() {
        List<Evento> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);
        list.add(entity);
        when(service.list()).thenReturn(list);

        ResponseEntity<List<EventoResponse>> response = controller.list();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_post() {
        when(mapper.fromDto(dto)).thenReturn(entity);
        when(service.createEvento(entity)).thenReturn(entity);
        when(serviceCategoria.findById(1)).thenReturn(categoria);
        when(serviceStatus.findById(1)).thenReturn(status);

        ResponseEntity<EventoResponse> response = controller.post(dto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_deleteById() {
        ResponseEntity<?> response = controller.deleteById(anyInt());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_cancelaEvento() {
        
    }
    
}