package com.example.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.project.domain.dto.request.ParticipacaoCreateRequest;
import com.example.project.domain.dto.response.ParticipacaoResponse;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.domain.mapper.ParticipacaoMapper;
import com.example.project.service.EventoService;
import com.example.project.service.ParticipacaoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RunWith(MockitoJUnitRunner.class)
public class ParticipacaoControllerTest {
    
    @Mock
    ParticipacaoMapper mapper;

    @Mock
    ParticipacaoService service;

    @Mock
    EventoService serviceEvento;

    @InjectMocks
    ParticipacaoController controller;

    CategoriaEvento categoria = CategoriaEvento.builder() //
        .IdCategoriaEvento(1) //
        .NomeCategoria("NomeCategoria") //
        .build();

    StatusEvento status = StatusEvento.builder() //
        .IdEventoStatus(1) //
        .NomeStatus("NomeStatus") //
        .build();

    Evento evento = Evento.builder() //
        .idEvento(1) //
        .categoriaEvento(categoria) //
        .statusEvento(status) //
        .nome("Nome") //
        .dataHoraInicio(new Date()) //
        .dataHoraFim(new Date()) //
        .local("Local") //
        .descricao("Descricao") //
        .limiteVagas(10) //
        .build();

    Participacao entity = Participacao.builder() //
        .IdParticipacao(1) //
        .evento(evento) //
        .LoginParticipante("LoginParticipante") // 
        .FlagPresente(true) //
        .Nota(10) //
        .Comentario("Comentario") //
        .build();
    
    ParticipacaoResponse responseDto = ParticipacaoResponse.builder() //
        .IdParticipacao(1) //
        .evento(evento) //
        .LoginParticipante("LoginParticipante") // 
        .FlagPresente(true) //
        .Nota(10) //
        .Comentario("Comentario") //
        .build();

    ParticipacaoCreateRequest dto = ParticipacaoCreateRequest.builder() //
        .IdEvento(evento.getIdEvento()) //
        .LoginParticipante("LoginParticipante") // 
        .build();

    @Test
    public void should_getById() {
        when(service.findById(anyInt())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(responseDto);

        ResponseEntity<ParticipacaoResponse> response = controller.getById(anyInt());

        assertEquals("Status deve ser Ok/200", response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_list() {
        List<Participacao> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);
        list.add(entity);
        when(service.list()).thenReturn(list);

        ResponseEntity<List<ParticipacaoResponse>> response = controller.list();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_post() {
        when(mapper.fromDto(dto)).thenReturn(entity);
        when(service.createParticipacao(entity)).thenReturn(entity);
        when(serviceEvento.findById(1)).thenReturn(evento);

        ResponseEntity<ParticipacaoResponse> response = controller.post(dto);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_deleteById() {
        ResponseEntity<?> response = controller.deleteById(anyInt());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}