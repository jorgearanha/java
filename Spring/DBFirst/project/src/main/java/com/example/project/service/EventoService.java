package com.example.project.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.project.util.MyDateUtil.zeraDia;
import static com.example.project.util.MyDateUtil.fimDia;
import static com.example.project.util.MyDateUtil.montaDate;
import static com.example.project.util.MyDateUtil.stringDate;

import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.entities.StatusEvento;
import com.example.project.exception.DataCantBeDeletedException;
import com.example.project.exception.DataNotFoundException;
import com.example.project.exception.EventoCantBeCanceledException;
import com.example.project.exception.EventoCantBeCreatedException;
import com.example.project.repository.EventoRepository;
import com.example.project.repository.ParticipacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    private final ParticipacaoRepository participacaoRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, ParticipacaoRepository participacaoRepository) {
        this.eventoRepository = eventoRepository;
        this.participacaoRepository = participacaoRepository;
    }

    public List<Evento> list() {
        return eventoRepository.findAll();
    }

    public List<Evento> listStatusAberto() {
        List<Evento> list = eventoRepository.findByStatusEvento(StatusEvento.builder().idEventoStatus(1).build());
        return list;
    }

    public List<Evento> listByDate(Date date) {
        System.out.println(montaDate(1, 1, 2020));
        List<Evento> list = eventoRepository.findPersonalizado(zeraDia(date));
        return list;
    }

    public List<Evento> listByCategoria(CategoriaEvento categoria) {
        List<Evento> list = eventoRepository.findByCategoriaEvento(categoria);
        return list;
    }

    public Evento findById(Integer id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElseThrow(() -> new DataNotFoundException("Evento Not found"));
    }

    public Evento createEvento(Evento evento) {
        if (evento.getDataHoraInicio().compareTo(evento.getDataHoraFim()) > 0
                || zeraDia(evento.getDataHoraInicio()).compareTo(zeraDia(evento.getDataHoraFim())) != 0)
            throw new EventoCantBeCreatedException(
                    "Show 🙏 - dataFim deve ser maior ou igual que dataInicio no mesmo dia.");
        return eventoRepository.save(evento);
    }

    public void deleteById(Integer id) {
        findById(id);
        try {
            eventoRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataCantBeDeletedException("Show 🙏 - Evento com participação não pode ser deletado.");
        }
    }

    public Evento cancelaEvento(Integer id) {
        Evento e = findById(id);

        if (participacaoRepository.findByEvento(e).size() > 0)
            throw new EventoCantBeCanceledException("Show 🙏 - Evento com participacao não pode ser cancelado.");

        if (zeraDia(e.getDataHoraInicio()).compareTo(zeraDia(Calendar.getInstance().getTime())) <= 0)
            throw new EventoCantBeCanceledException(
                    "Show 🙏 - Eventos de hoje, ou passados, não podem ser cancelados.");

        e.setStatusEvento(StatusEvento.builder() //
                .idEventoStatus(4) //
                .nomeStatus("Cancelado") //
                .build());

        eventoRepository.save(e);

        return e;
    }

    public Evento putEvento(Integer id, Evento model) {
        Evento evento = findById(id);

        evento.setStatusEvento(model.getStatusEvento());
        evento.setLocal(model.getLocal());
        evento.setDescricao(model.getDescricao());
        evento.setLimiteVagas(model.getLimiteVagas());

        eventoRepository.save(evento);

        return evento;
    }

}