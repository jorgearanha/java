package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.CategoriaEventoRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaEventoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    CategoriaEventoRepository repositoryMock;

    @InjectMocks
    private CategoriaEventoService service;

    CategoriaEvento entity = CategoriaEvento
        .builder() //
        .IdCategoriaEvento(1) //
        .NomeCategoria("NomeCategoria") //
        .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound(){
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("CategoriaEvento Not found");

        service.findById(1);
    }

    @Test
    public void should_findById() {
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(entity));

        CategoriaEvento model = service.findById(anyInt());

        assertEquals("Valores diferentes encontrados", entity, model);
    }

    @Test
    public void should_list() {
        List<CategoriaEvento> list = new ArrayList<>();
        list.add(entity);
        list.add(entity);

        when(repositoryMock.findAll()).thenReturn(list);

        List<CategoriaEvento> categorias = service.list();

        verify(repositoryMock, times(1)).findAll();
        
        assertTrue("O retorno esperado é uma lista", categorias instanceof List<?>);
        assertTrue("A Lista deve ser de CategoriaEvento", categorias.get(0) instanceof CategoriaEvento);
    }

}