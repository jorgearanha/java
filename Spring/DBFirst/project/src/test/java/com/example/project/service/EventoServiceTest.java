package com.example.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.example.project.util.MyDateUtil.stringDate;

import static com.example.project.util.MyDateUtil.dayPlusOne;
import static com.example.project.util.MyDateUtil.zeraDia;
import static com.example.project.util.MyDateUtil.fimDia;
import static com.example.project.util.MyDateUtil.maiorOuIgual;
import static com.example.project.util.MyDateUtil.menorOuIgual;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataCantBeDeletedException;
import com.example.project.exception.DataNotFoundException;
import com.example.project.exception.EventoCantBeCanceledException;
import com.example.project.exception.EventoCantBeCreatedException;
import com.example.project.repository.EventoRepository;
import com.example.project.repository.ParticipacaoRepository;

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
        ParticipacaoRepository repositoryParticipacao;

        @Mock
        private StatusEventoService serviceStatus;

        @InjectMocks
        private EventoService service;

        CategoriaEvento categoria = CategoriaEvento.builder() //
                        .idCategoriaEvento(1) //
                        .nomeCategoria("NomeCategoria") //
                        .build();

        StatusEvento status = StatusEvento.builder() //
                        .idEventoStatus(1) //
                        .nomeStatus("NomeStatus") //
                        .build();

        Evento evento = Evento.builder() //
                        .idEvento(1) //
                        .categoriaEvento(categoria) //
                        .statusEvento(status) //
                        .nome("Nome") //
                        .dataHoraInicio(dayPlusOne()) //
                        .dataHoraFim(dayPlusOne()) //
                        .local("Local") //
                        .descricao("Descricao") //
                        .limiteVagas(10) //
                        .build();

        Participacao participacao = Participacao.builder() //
                        .idParticipacao(1) //
                        .evento(evento) //
                        .loginParticipante("LoginParticipante") //
                        .flagPresente(false) //
                        .nota(10) //
                        .comentario("Comentario") //
                        .build();

        @Test
        public void should_ThrowDataNotFoundException_whenNotFound() {
                expected.expect(DataNotFoundException.class);
                expected.expectMessage("Evento Not found");

                service.findById(1);
        }

        @Test
        public void should_ThrowDataCantBeDeletedException_whenNotFound() {
                doThrow(new DataCantBeDeletedException("Show 🙏")).when(repositoryMock).deleteById(anyInt());

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
                                .idEvento(1) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(dayPlusOne()) //
                                .dataHoraFim(dayPlusTwo.getTime()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
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
                                .idEvento(1) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(dayPlusOne()) //
                                .dataHoraFim(dayMinusOneMilli.getTime()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();

                service.createEvento(teste);

        }

        @Test
        public void should_ThrowDataCantBeCanceledException_whenExistsParticipacao() {
                expected.expect(EventoCantBeCanceledException.class);
                expected.expectMessage("Show 🙏 - Evento com participacao não pode ser cancelado.");

                List<Participacao> participacoes = new ArrayList<>();
                participacoes.add(participacao);

                when(repositoryMock.findById(anyInt()))//
                                .thenReturn(Optional.of(evento));
                when(repositoryParticipacao.findByEvento(evento)) //
                                .thenReturn(participacoes);

                service.cancelaEvento(anyInt());

        }

        @Test
        public void should_ThrowDataCantBeCanceledException_whenEventIsToday() {
                expected.expect(EventoCantBeCanceledException.class);
                expected.expectMessage("Show 🙏 - Eventos de hoje, ou passados, não podem ser cancelados.");

                Evento teste = Evento.builder() //
                                .idEvento(1) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(Calendar.getInstance().getTime())//
                                .dataHoraFim(Calendar.getInstance().getTime()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();

                when(repositoryMock.findById(anyInt()))//
                                .thenReturn(Optional.of(teste));
                when(repositoryParticipacao.findByEvento(teste)) //
                                .thenReturn(new ArrayList<>());

                service.cancelaEvento(anyInt());

        }

        @Test
        public void should_ThrowDataCantBeCanceledException_whenDatePassed() {
                expected.expect(EventoCantBeCanceledException.class);
                expected.expectMessage("Show 🙏 - Eventos de hoje, ou passados, não podem ser cancelados.");

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, -1);

                Evento teste = Evento.builder() //
                                .idEvento(1) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(cal.getTime()) //
                                .dataHoraFim(cal.getTime()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();

                when(repositoryMock.findById(anyInt()))//
                                .thenReturn(Optional.of(teste));
                when(repositoryParticipacao.findByEvento(teste)) //
                                .thenReturn(new ArrayList<>());

                service.cancelaEvento(anyInt());

        }

        @Test
        public void should_findById() {
                when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(evento));

                Evento model = service.findById(anyInt());

                assertEquals("Saida esperada não ocorreu", evento, model);
        }

        @Test
        public void should_list() {
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
        public void should_listStatusAberto() {
                List<Evento> list = new ArrayList<>();
                list.add(evento);
                list.add(evento);

                when(repositoryMock.findByStatusEvento(any())).thenReturn(list);

                List<Evento> listModel = service.listStatusAberto();

                verify(repositoryMock, times(1)).findByStatusEvento(any());

                assertTrue("O retorno deve ser uma lista!", listModel instanceof List<?>);
                assertTrue("A lista de retorno deve ser de StatusEvento!", listModel.get(0) instanceof Evento);

                for (Evento evento : listModel) {
                        assertTrue("O evento deve estar aberto para inscrição!",
                                        evento.getStatusEvento().getIdEventoStatus() == 1);
                }

        }

        @Test
        public void should_listByDate() {
                List<Evento> list = new ArrayList<>();
                list.add(evento);
                list.add(evento);

                when(repositoryMock.findByDataHoraInicioBetween(any(), any())).thenReturn(list);

                List<Evento> listModel = service.listByDate(dayPlusOne());

                verify(repositoryMock, times(1)).findByDataHoraInicioBetween(stringDate(zeraDia(dayPlusOne())),
                                stringDate(fimDia(dayPlusOne())));

                assertTrue("O retorno deve ser uma lista!", listModel instanceof List<?>);
                assertTrue("A lista de retorno deve ser de StatusEvento!", listModel.get(0) instanceof Evento);

                for (Evento evento : listModel) {
                        assertTrue("O evento deve estar no intervalo do dia", //
                                        maiorOuIgual(evento.getDataHoraInicio(), zeraDia(dayPlusOne())) //
                                                        && menorOuIgual(evento.getDataHoraInicio(),
                                                                        fimDia(dayPlusOne())));
                }
        }

        @Test
        public void should_listByCategoria() {
                List<Evento> list = new ArrayList<>();
                list.add(evento);
                list.add(evento);

                when(repositoryMock.findByCategoriaEvento(categoria)).thenReturn(list);

                List<Evento> listModel = service.listByCategoria(categoria);

                verify(repositoryMock, times(1)).findByCategoriaEvento(categoria);

                assertTrue("O retorno deve ser uma lista", listModel instanceof List<?>);
                assertTrue("A lista de retorno deve ser de StatusEvento", listModel.get(0) instanceof Evento);

                for (Evento evento : listModel) {
                        assertTrue("O evento deve estar na categoria indicada", //
                                        evento.getCategoriaEvento().equals(categoria));
                }
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
                                .thenReturn(StatusEvento.builder().idEventoStatus(4).nomeStatus("Cancelado").build());
                when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(evento));

                Evento teste = Evento.builder() //
                                .statusEvento(serviceStatus.findById(4)) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();

                Evento model = service.putEvento(1, teste);

                assertEquals("Saida diferento do esperado", model, Evento.builder() //
                                .idEvento(1) //
                                .categoriaEvento(categoria) //
                                .statusEvento(serviceStatus.findById(4)) //
                                .nome("Nome") //
                                .dataHoraInicio(evento.getDataHoraInicio()) //
                                .dataHoraFim(evento.getDataHoraFim())//
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build());
        }

        @Test
        public void should_cancelaEvento() {
                when(repositoryMock.findById(anyInt())).thenReturn(Optional.of(evento));
                when(repositoryParticipacao.findByEvento(evento)).thenReturn(new ArrayList<Participacao>());

                Evento teste = service.cancelaEvento(1);

                assertEquals("Status evento deveria estar como Cancelado", teste.getStatusEvento(),
                                StatusEvento.builder().idEventoStatus(4).nomeStatus("Cancelado").build());
        }

}