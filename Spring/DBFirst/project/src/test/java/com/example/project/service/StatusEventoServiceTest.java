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

import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataNotFoundException;
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

    // CategoriaEvento categoria = CategoriaEvento.builder() //
    //         .IdCategoriaEvento(1) //
    //         .NomeCategoria("NomeCategoria") //
    //         .build();

    StatusEvento status = StatusEvento.builder() //
            .IdEventoStatus(1) //
            .NomeStatus("NomeStatus") //
            .build();

    // Evento evento = Evento.builder() //
    //         .IdEvento(1) //
    //         .categoriaEvento(categoria) //
    //         .statusEvento(status) //
    //         .Nome("Nome") //
    //         .DataHoraInicio(new Date()) //
    //         .DataHoraFim(new Date()) //
    //         .Local("Local") //
    //         .Descricao("Descricao") //
    //         .LimiteVagas(10) //
    //         .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("StatusEvento Not found");

        service.findById(1);
    }

    @Test
    public void should_findById() {
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(status));

        StatusEvento model = service.findById(anyInt());

        assertEquals("Saida esperada não ocorreu" , status, model);
    }

    @Test
    public void should_listClient() {
        List<StatusEvento> list = new ArrayList<>();
        list.add(status);
        list.add(status);
        when(repositoryMock.findAll()).thenReturn(list);

        List<StatusEvento> listModel = service.list();

        verify(repositoryMock, times(1)).findAll();

        assertTrue("O retorno deve ser uma lista", listModel instanceof List<?>);
        assertTrue("A lista de retorno deve ser de StatusEvento", listModel.get(0) instanceof StatusEvento);
    }

}