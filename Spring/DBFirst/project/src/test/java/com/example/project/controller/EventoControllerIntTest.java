package com.example.project.controller;

import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.request.EventoUpdateRequest;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.repository.CategoriaEventoRepository;
import com.example.project.repository.EventoRepository;
import com.example.project.repository.StatusEventoRepository;
import com.example.project.utils.IntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.example.project.util.MyDateUtil.dayPlusOne;
import static com.example.project.util.MyDateUtil.datePlus;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.List;

/**
 * EventoControllerIntTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = IntegrationTestConfig.appProperties)
@ActiveProfiles("test")
public class EventoControllerIntTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper mapper;

        @Autowired
        private EventoRepository eventoRepository;

        @Autowired
        private CategoriaEventoRepository categoriaEventoRepository;

        @Autowired
        private StatusEventoRepository statusEventoRepository;

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
                        .dataHoraInicio(datePlus(dayPlusOne(), 5)) //
                        .dataHoraFim(datePlus(dayPlusOne(), 5)) //
                        .local("Local") //
                        .descricao("Descricao") //
                        .limiteVagas(10) //
                        .build();

        EventoCreateRequest request = EventoCreateRequest.builder() //
                        .IdCategoriaEvento(categoria.getIdCategoriaEvento()) //
                        .IdEventoStatus(status.getIdEventoStatus()) //
                        .Nome("Nome") //
                        .DataHoraInicio(datePlus(dayPlusOne(), 5)) //
                        .DataHoraFim(datePlus(dayPlusOne(), 5)) //
                        .Local("Local") //
                        .Descricao("Descricao") //
                        .LimiteVagas(10) //
                        .build();

        @Test
        public void should_getById() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                eventoRepository.saveAndFlush(evento);

                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/evento/" + evento.getIdEvento())) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()) //
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                .andReturn();

                Evento respostaEvento = mapper.readValue(result.getResponse().getContentAsString(), Evento.class);

                assertEquals("Reposta esperada inválida!", evento, respostaEvento);
        }

        @Test
        public void shouldReturn404_WhenGetByIdIsInvalid() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                eventoRepository.saveAndFlush(evento);

                mockMvc.perform(MockMvcRequestBuilders.get("/evento/1000")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void should_list() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/evento")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()) //
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentType(MediaType.APPLICATION_JSON_UTF8));
        }

        @Test
        public void should_listStatusAberto() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/evento/status_aberto")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()) //
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentType(MediaType.APPLICATION_JSON_UTF8));
        }

        @Test
        public void should_listByCategoria() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);

                mockMvc.perform(MockMvcRequestBuilders.get("/evento/categoria=1")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()) //
                                .andExpect(MockMvcResultMatchers.content()
                                                .contentType(MediaType.APPLICATION_JSON_UTF8));
        }

        @Test
        public void shouldReturn404_WhenListByCategoriaIsInvalid() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);

                mockMvc.perform(MockMvcRequestBuilders.get("/evento/categoria=1000")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void should_post() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);

                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/evento") //
                                .contentType(MediaType.APPLICATION_JSON) //
                                .content(mapper.writeValueAsString(request))) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()) //
                                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                .andReturn();

                Evento respostaEvento = mapper.readValue(result.getResponse().getContentAsString(), Evento.class);

                assertEquals("Reposta esperada inválida!", request.getNome(), respostaEvento.getNome());
                assertEquals("Reposta esperada inválida!", request.getLocal(), respostaEvento.getLocal());
                assertEquals("Reposta esperada inválida!", request.getDataHoraInicio(),
                                respostaEvento.getDataHoraInicio());
                assertEquals("Reposta esperada inválida!", request.getDataHoraFim(), respostaEvento.getDataHoraFim());
                assertEquals("Reposta esperada inválida!", request.getLimiteVagas(), respostaEvento.getLimiteVagas());
                assertEquals("Reposta esperada inválida!", request.getDescricao(), respostaEvento.getDescricao());
                assertEquals("Reposta esperada inválida!", request.getIdCategoriaEvento(),
                                respostaEvento.getCategoriaEvento().getIdCategoriaEvento());
                assertEquals("Reposta esperada inválida!", request.getIdEventoStatus(),
                                respostaEvento.getStatusEvento().getIdEventoStatus());

        }

        @Test
        public void shouldReturn404_whenPostIsInvalid() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);

                EventoCreateRequest requestFailed = EventoCreateRequest.builder() //
                                .IdCategoriaEvento(2) // invalido
                                .IdEventoStatus(2) // invalido
                                .Nome("Nome") //
                                .DataHoraInicio(new Date()) //
                                .DataHoraFim(new Date()) //
                                .Local("Local") //
                                .Descricao("Descricao") //
                                .LimiteVagas(10) //
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.post("/evento") //
                                .contentType(MediaType.APPLICATION_JSON) //
                                .content(mapper.writeValueAsString(requestFailed))) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isNotFound());

        }

        @Test
        public void should_deleteById() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                eventoRepository.saveAndFlush(evento);

                mockMvc.perform(MockMvcRequestBuilders.delete("/evento/" + evento.getIdEvento())) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk());
        }

        @Test
        public void shouldReturn404_WhenDeleteByIdIsInvalid() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                eventoRepository.saveAndFlush(evento);

                mockMvc.perform(MockMvcRequestBuilders.delete("/evento/1000")) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void should_put() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository
                                .saveAndFlush(StatusEvento.builder().idEventoStatus(2).nomeStatus("nome").build());
                eventoRepository.saveAndFlush(evento);

                EventoUpdateRequest eventoUpdateRequest = EventoUpdateRequest.builder() //
                                .Descricao("put") //
                                .IdEventoStatus(2) //
                                .LimiteVagas(42) //
                                .Local("put") //
                                .build();

                MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/evento/" + evento.getIdEvento()) //
                                .contentType(MediaType.APPLICATION_JSON) //
                                .content(mapper.writeValueAsString(eventoUpdateRequest))) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

                Evento respostaEvento = mapper.readValue(result.getResponse().getContentAsString(), Evento.class);

                assertEquals("message", respostaEvento, //
                                Evento.builder() //
                                                .idEvento(evento.getIdEvento()) //
                                                .descricao("put") //
                                                .statusEvento(StatusEvento.builder().idEventoStatus(2).build()) //
                                                .limiteVagas(42) //
                                                .local("put") //
                                                .categoriaEvento(categoria) //
                                                .nome("Nome") //
                                                .dataHoraInicio(evento.getDataHoraInicio()) //
                                                .dataHoraFim(evento.getDataHoraFim()) //
                                                .build());
        }

        @Test
        public void shouldReturn404_WhenPutIsInvalid() throws Exception {
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository
                                .saveAndFlush(StatusEvento.builder().idEventoStatus(2).nomeStatus("nome").build());
                eventoRepository.saveAndFlush(evento);

                EventoUpdateRequest eventoUpdateRequest = EventoUpdateRequest.builder() //
                                .Descricao("put") //
                                .IdEventoStatus(2) //
                                .LimiteVagas(42) //
                                .Local("put") //
                                .build();

                mockMvc.perform(MockMvcRequestBuilders.put("/evento/1000") //
                                .contentType(MediaType.APPLICATION_JSON) //
                                .content(mapper.writeValueAsString(eventoUpdateRequest))) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isNotFound());
        }

        @Test
        public void shouldReturn405_WhenCancelaIsInvalid() throws Exception {

                Evento e = Evento.builder() //
                                .idEvento(2) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(new Date()) //
                                .dataHoraFim(new Date()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();
                                
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(StatusEvento.builder() //
                                .idEventoStatus(4) //
                                .nomeStatus("Cancelado") //
                                .build());
                eventoRepository.saveAndFlush(evento);
                eventoRepository.saveAndFlush(e);

                mockMvc.perform(MockMvcRequestBuilders.put("/evento/cancela/2") //
                                .contentType(MediaType.APPLICATION_JSON)) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());

        }

        @Test
        public void shold_cancela() throws Exception {

                Evento e = Evento.builder() //
                                .idEvento(2) //
                                .categoriaEvento(categoria) //
                                .statusEvento(status) //
                                .nome("Nome") //
                                .dataHoraInicio(new Date()) //
                                .dataHoraFim(new Date()) //
                                .local("Local") //
                                .descricao("Descricao") //
                                .limiteVagas(10) //
                                .build();
                                
                categoriaEventoRepository.saveAndFlush(categoria);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(status);
                statusEventoRepository.saveAndFlush(StatusEvento.builder() //
                                .idEventoStatus(4) //
                                .nomeStatus("Cancelado") //
                                .build());
                eventoRepository.saveAndFlush(evento);
                eventoRepository.saveAndFlush(e);

                mockMvc.perform(MockMvcRequestBuilders.put("/evento/cancela/1") //
                                .contentType(MediaType.APPLICATION_JSON)) //
                                .andDo(MockMvcResultHandlers.print()) //
                                .andExpect(MockMvcResultMatchers.status().isOk());

        }
}