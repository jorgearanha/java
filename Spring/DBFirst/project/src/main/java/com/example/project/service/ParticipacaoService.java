package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.Participacao;
import com.example.project.exception.DataNotFoundException;
import com.example.project.exception.ParticipacaoCantBeCreatedException;
import com.example.project.repository.ParticipacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;

    private final EventoService eventoService;

    @Autowired
    public ParticipacaoService(ParticipacaoRepository participacaoRepository, EventoService eventoService) {
        this.participacaoRepository = participacaoRepository;
        this.eventoService = eventoService;
    }

    public List<Participacao> list() {
        return participacaoRepository.findAll();
    }

    public Participacao findById(Integer id) {
        Optional<Participacao> participacao = participacaoRepository.findById(id);
        return participacao.orElseThrow(() -> new DataNotFoundException("Participacao Not found"));
    }

    public Participacao createParticipacao(Participacao participacao) {
        Evento evento = eventoService.findById(participacao.getEvento().getIdEvento());

        if (countByEvento(evento) >= evento.getLimiteVagas()
                || evento.getStatusEvento().getIdEventoStatus() != 1)
            throw new ParticipacaoCantBeCreatedException("Evento is full or is closed");

        return participacaoRepository.save(participacao);
    }

    public Integer countByEvento(Evento evento) {
        return participacaoRepository.countByEvento(evento);
    }

    public Participacao putFlag(Integer id) {
        Participacao participacao = findById(id);
        participacao.setFlagPresente(true);

        participacaoRepository.save(participacao);

        return participacao;
    }

    public Participacao putFeedback(Integer id, Participacao model) {
        Participacao participacao = findById(id);

        participacao.setNota(model.getNota());
        participacao.setComentario(model.getComentario());

        return participacao;
    }

    public void deleteById(Integer id) {
        findById(id);
        participacaoRepository.deleteById(id);
    }

}