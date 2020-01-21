package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.CategoriaEventoRepository;
import com.example.project.repository.StatusEventoRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatusEventoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    StatusEventoRepository repositoryMock;

    @InjectMocks
    private StatusEventoService service;

    CategoriaEvento entity = CategoriaEvento.builder() //
            .IdCategoriaEvento(1) //
            .NomeCategoria("NomeCategoria") //
            .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("StatusEvento Not found");

        service.findById(1);
    }

}