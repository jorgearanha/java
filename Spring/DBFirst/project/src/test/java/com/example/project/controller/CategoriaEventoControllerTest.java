package com.example.project.controller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.example.project.domain.dto.response.CategoriaEventoResponse;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.mapper.CategoriaEventoMapper;
import com.example.project.service.CategoriaEventoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@RunWith(MockitoJUnitRunner.class)
public class CategoriaEventoControllerTest {
    
    @Mock
    CategoriaEventoMapper mapper;

    @Mock
    CategoriaEventoService service;

    @InjectMocks
    CategoriaEventoController controller;

    CategoriaEvento entity = CategoriaEvento.builder() //
        .IdCategoriaEvento(1) //
        .NomeCategoria("NomeCategoria") //
        .build();

    CategoriaEventoResponse responseDto = CategoriaEventoResponse.builder() //
        .IdCategoriaEvento(1) //
        .NomeCategoria("NomeCategoria") //
        .build();

    @Test
    public void should_getById() {
        when(service.findById(anyInt())).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(responseDto);

        ResponseEntity<CategoriaEventoResponse> response = controller.getById(anyInt());

        assertEquals("Status deve ser Ok/200", response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void should_list() {
        List<CategoriaEvento> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);
        list.add(entity);
        when(service.list()).thenReturn(list);

        ResponseEntity<List<CategoriaEventoResponse>> response = controller.list();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
}