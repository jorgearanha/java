package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.Participacao;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.ParticipacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipacaoService {

    private final ParticipacaoRepository participacaoRepository;

    @Autowired
    public ParticipacaoService(ParticipacaoRepository participacaoRepository) {
        this.participacaoRepository = participacaoRepository;
    }

    public List<Participacao> list() {
        return participacaoRepository.findAll();
    }

    public Participacao findById(Integer id) {
        Optional<Participacao> participacao = participacaoRepository.findById(id);
        return participacao.orElseThrow(() -> new DataNotFoundException("Participacao Not found"));
    }

    public Participacao createParticipacao(Participacao participacao) {
        return participacaoRepository.save(participacao);
    }

    public Participacao putParticipacao(Integer id ,Participacao model) {
        Participacao participacao = findById(id);
        return null;
    }

    public void deleteById(Integer id){
        findById(id);
        participacaoRepository.deleteById(id);
    }

}