package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ParticipacaoRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParticipacaoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    ParticipacaoRepository repositoryMock;

    @InjectMocks
    private ParticipacaoService service;

    CategoriaEvento categoria = CategoriaEvento.builder() //
            .IdCategoriaEvento(1) //
            .NomeCategoria("NomeCategoria") //
            .build();

    StatusEvento status = StatusEvento.builder() //
            .IdEventoStatus(1) //
            .NomeStatus("NomeStatus") //
            .build();

    Evento evento = Evento.builder() //
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

    Participacao participacao = Participacao.builder() //
            .IdParticipacao(1) //
            .evento(evento) //
            .LoginParticipante("LoginParticipante") //
            .FlagPresente(false) //
            .Nota(10) //
            .Comentario("Comentario") //
            .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("Participacao Not found");

        service.findById(1);
    }

    @Test
    public void should_findById() {
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(participacao));

        Participacao model = service.findById(anyInt());

        assertEquals("Saida esperada não ocorreu", participacao, model);
    }

    @Test
    public void should_list() {
        List<Participacao> list = new ArrayList<>();
        list.add(participacao);
        list.add(participacao);
        when(repositoryMock.findAll()).thenReturn(list);

        List<Participacao> listModel = service.list();

        verify(repositoryMock, times(1)).findAll();

        assertTrue("O retorno deve ser uma lista", listModel instanceof List<?>);
        assertTrue("A lista de retorno deve ser de StatusEvento", listModel.get(0) instanceof Participacao);
    }

    @Test
    public void should_createParticipacao() {
        when(repositoryMock.save(participacao)).thenReturn(participacao);

        Participacao participacaoModel = service.createParticipacao(participacao);

        assertEquals("Saida esperada não ocorreu", participacaoModel, participacao);
    }

    @Test
    public void should_putFlag() {

        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(participacao));

        Participacao teste = service.putFlag(1);

        assertEquals(teste, Participacao.builder() //
                .IdParticipacao(1) //
                .evento(evento) //
                .LoginParticipante("LoginParticipante") //
                .FlagPresente(true) //
                .Nota(10) //
                .Comentario("Comentario") //
                .build());
    }

    @Test
    public void should_putFeedback() {

        Participacao model = Participacao.builder() //
                .Comentario("Nunca fui, muito bom") // 
                .Nota(1)
                .build();

        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(participacao));

        Participacao teste = service.putFeedback(1, model);

        assertEquals(teste, Participacao.builder() //
                .IdParticipacao(1) //
                .evento(evento) //
                .LoginParticipante("LoginParticipante") //
                .FlagPresente(false) //
                .Comentario("Nunca fui, muito bom") // 
                .Nota(1)
                .build());
    }

    @Test
    public void should_deleteById() {
        should_findById();
        service.deleteById(1);
    }

}