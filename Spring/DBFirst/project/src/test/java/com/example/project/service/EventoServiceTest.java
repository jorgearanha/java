package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataCantBeDeletedException;
import com.example.project.exception.DataNotFoundException;
import com.example.project.exception.EventoCantBeCreatedException;
import com.example.project.repository.EventoRepository;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EventoServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Mock
    EventoRepository repositoryMock;

    @Mock
    private StatusEventoService serviceStatus;

    @InjectMocks
    private EventoService service;

    CategoriaEvento categoria = CategoriaEvento.builder() //
            .IdCategoriaEvento(1) //
            .NomeCategoria("NomeCategoria") //
            .build();

    StatusEvento status = StatusEvento.builder() //
            .IdEventoStatus(1) //
            .NomeStatus("NomeStatus") //
            .build();

    public Date dayPlusOne() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return cal.getTime();
    }
    

    Evento evento = Evento.builder() //
            .IdEvento(1) //
            .categoriaEvento(categoria) //
            .statusEvento(status) //
            .Nome("Nome") //
            .DataHoraInicio(dayPlusOne()) //
            .DataHoraFim(dayPlusOne()) //
            .Local("Local") //
            .Descricao("Descricao") //
            .LimiteVagas(10) //
            .build();

    @Test
    public void should_ThrowDataNotFoundException_whenNotFound() {
        expected.expect(DataNotFoundException.class);
        expected.expectMessage("Evento Not found");

        service.findById(1);
    }

    @Test
    public void should_ThrowDataCantBeDeletedException_whenNotFound() {
        doThrow(new DataCantBeDeletedException("Show 🙏"))
            .when(repositoryMock).deleteById(anyInt());

        expected.expect(DataCantBeDeletedException.class);
        expected.expectMessage("Show 🙏 - Evento com participação não pode ser deletado.");
        
        should_findById();

        service.deleteById(1);
    }

    @Test
    public void should_ThrowEventoCantBeCreatedException_WhenDifferentDay() {
        expected.expect(EventoCantBeCreatedException.class);
        expected.expectMessage("Show 🙏 - dataFim deve ser maior ou igual que dataInicio no mesmo dia.");

        Calendar dayPlusTwo = Calendar.getInstance();

        dayPlusTwo.setTime(dayPlusOne());
        dayPlusTwo.add(Calendar.DAY_OF_MONTH, 1);

        Evento teste = Evento.builder() //
            .IdEvento(1) //
            .categoriaEvento(categoria) //
            .statusEvento(status) //
            .Nome("Nome") //
            .DataHoraInicio(dayPlusOne()) //
            .DataHoraFim(dayPlusTwo.getTime()) //
            .Local("Local") //
            .Descricao("Descricao") //
            .LimiteVagas(10) //
            .build();

        service.createEvento(teste);

    }

    @Test
    public void should_ThrowEventoCantBeCreatedException_WhenFimGreaterThanIni() {
        expected.expect(EventoCantBeCreatedException.class);
        expected.expectMessage("Show 🙏 - dataFim deve ser maior ou igual que dataInicio no mesmo dia.");

        Calendar dayMinusOneMilli = Calendar.getInstance();

        dayMinusOneMilli.setTime(dayPlusOne());
        dayMinusOneMilli.add(Calendar.MILLISECOND, -1);

        Evento teste = Evento.builder() //
            .IdEvento(1) //
            .categoriaEvento(categoria) //
            .statusEvento(status) //
            .Nome("Nome") //
            .DataHoraInicio(dayPlusOne()) //
            .DataHoraFim(dayMinusOneMilli.getTime()) //
            .Local("Local") //
            .Descricao("Descricao") //
            .LimiteVagas(10) //
            .build();

        service.createEvento(teste);

    }

    @Test
    public void should_findById() {
        when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(evento));

        Evento model = service.findById(anyInt());

        assertEquals("Saida esperada não ocorreu" , evento, model);
    }

    @Test
    public void should_listClient() {
        List<Evento> list = new ArrayList<>();
        list.add(evento);
        list.add(evento);
        when(repositoryMock.findAll()).thenReturn(list);

        List<Evento> listModel = service.list();

        verify(repositoryMock, times(1)).findAll();

        assertTrue("O retorno deve ser uma lista", listModel instanceof List<?>);
        assertTrue("A lista de retorno deve ser de StatusEvento", listModel.get(0) instanceof Evento);
    }

    @Test
    public void should_createEvento() {
        when(repositoryMock.save(evento)).thenReturn(evento);

        Evento eventoModel = service.createEvento(evento);

        assertEquals("Saida esperada não ocorreu", eventoModel, evento);
    }

    @Test
    public void should_deleteById() {
        should_findById();
        service.deleteById(1);
    }

    @Test
    public void should_PutEvento() {
        when(serviceStatus.findById(4))
            .thenReturn(StatusEvento.builder().IdEventoStatus(4).NomeStatus("Cancelado").build());
        when(repositoryMock.findById(anyInt()))
            .thenReturn(Optional.of(evento));

            Evento teste = Evento.builder() //
                .statusEvento(serviceStatus.findById(4)) //
                .Local("Local") //
                .Descricao("Descricao") //
                .LimiteVagas(10) //
                .build(); 
        
        Evento model = service.putEvento(1, teste);

        assertEquals("Saida diferento do esperado",
            model, 
            Evento.builder() //
                .IdEvento(1) //
                .categoriaEvento(categoria) //
                .statusEvento(serviceStatus.findById(4)) //
                .Nome("Nome") //
                .DataHoraInicio(evento.getDataHoraInicio()) //
                .DataHoraFim(evento.getDataHoraFim())//
                .Local("Local") //
                .Descricao("Descricao") //
                .LimiteVagas(10) //
                .build());
    }

    @Test
    public void should_cancelaEvento(){
        when(repositoryMock.findById(anyInt()))
            .thenReturn(Optional.of(evento));

        Evento teste = service.cancelaEvento(1);

        assertEquals("Status evento deveria estar como Cancelado",
            teste.getStatusEvento(), 
            StatusEvento.builder().IdEventoStatus(4).NomeStatus("Cancelado").build());
    }

}