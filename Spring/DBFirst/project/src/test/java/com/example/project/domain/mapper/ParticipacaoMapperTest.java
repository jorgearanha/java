package com.example.project.domain.mapper;

import static org.junit.Assert.assertEquals;

import com.example.project.configuration.MapperConfig;
import com.example.project.domain.dto.request.ParticipacaoCreateRequest;
import com.example.project.domain.dto.response.ParticipacaoResponse;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Spy;

@RunWith(MockitoJUnitRunner.class)
public class ParticipacaoMapperTest {

    @Spy
    private ModelMapper modelMapper = new MapperConfig().getModelMapper();

    @InjectMocks
    private ParticipacaoMapper mapper;

    @Test
    public void should_Convert_ParticipacaoToParticipacaoResponse() {
        Participacao p = Participacao
            .builder()
            .IdParticipacao(1)
            .evento(Evento.builder().IdEvento(1).build())
            .LoginParticipante("LoginParticipante")
            .FlagPresente(true)
            .Nota(10)
            .Comentario("Comentario")
            .build();

        ParticipacaoResponse dto = mapper.toDto(p);

        assertEquals("Valores diferentes encontrados 1", dto.getIdParticipacao(), p.getIdParticipacao());
        assertEquals("Valores diferentes encontrados 2", dto.getEvento().getIdEvento(), p.getEvento().getIdEvento());
        assertEquals("Valores diferentes encontrados 3", dto.getLoginParticipante(), p.getLoginParticipante());
        assertEquals("Valores diferentes encontrados 4", dto.getFlagPresente(), p.getFlagPresente());
        assertEquals("Valores diferentes encontrados 5", dto.getNota(), p.getNota());
        assertEquals("Valores diferentes encontrados 6", dto.getComentario(), p.getComentario());
    }

    @Test
    public void should_Convert_ParticipacaoResponseToParticipacao() {
        ParticipacaoCreateRequest dto = ParticipacaoCreateRequest
            .builder()
            .IdEvento(1)
            .LoginParticipante("LoginParticipante")
            .build();

        Participacao p = mapper.fromDto(dto);
        p.setEvento(Evento.builder().IdEvento(1).build());

        assertEquals("Valores diferentes encontrados 1", dto.getIdEvento(), p.getEvento().getIdEvento());
        assertEquals("Valores diferentes encontrados 2", dto.getLoginParticipante(), p.getLoginParticipante());
    }

}