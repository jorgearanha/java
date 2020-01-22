package com.example.project.controller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.project.domain.dto.response.StatusEventoResponse;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.domain.mapper.StatusEventoMapper;
import com.example.project.service.StatusEventoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RunWith(MockitoJUnitRunner.class)
public class StatusEventoControllerTest {
    
    @Mock
    StatusEventoMapper mapper;

    @Mock
    StatusEventoService service;

    @InjectMocks
    StatusEventoController controller;

    StatusEvento entity = StatusEvento.builder() //
            .IdEventoStatus(1) //
            .NomeStatus("NomeStatus") //
            .build();

    StatusEventoResponse responseDto = StatusEventoResponse.builder() //
            .IdEventoStatus(1) //
            .NomeStatus("NomeStatus") //
            .build();

    @Test
    public void should_getById() {
        when(service.findById(anyInt())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(responseDto);

        ResponseEntity<StatusEventoResponse> response = controller.getById(anyInt());

        assertEquals("Status deve ser Ok/200", response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_list() {
        List<StatusEvento> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);
        list.add(entity);
        when(service.list()).thenReturn(list);

        ResponseEntity<List<StatusEventoResponse>> response = controller.list();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}