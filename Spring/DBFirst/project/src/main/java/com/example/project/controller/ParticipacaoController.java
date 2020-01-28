package com.example.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.project.domain.dto.request.ParticipacaoCreateRequest;
import com.example.project.domain.dto.response.ParticipacaoResponse;
import com.example.project.domain.entities.Participacao;
import com.example.project.domain.mapper.ParticipacaoMapper;
import com.example.project.service.EventoService;
import com.example.project.service.ParticipacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/participacao")
public class ParticipacaoController {

	private final ParticipacaoService participacaoService;
	private final EventoService eventoService;
	private final ParticipacaoMapper mapper;

	@Autowired
	public ParticipacaoController(EventoService eventoService, ParticipacaoService participacaoService, ParticipacaoMapper participacaoMapper) {
		this.participacaoService = participacaoService;
		this.eventoService = eventoService;
		this.mapper = participacaoMapper;
	}

	@GetMapping(value = "/{id}")
    public ResponseEntity<ParticipacaoResponse> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(mapper.toDto(participacaoService.findById(id))) ;
    }
	
	@GetMapping
    public ResponseEntity<List<ParticipacaoResponse>> list() {
		return ResponseEntity.ok(participacaoService.list().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ParticipacaoResponse> post(@RequestBody ParticipacaoCreateRequest model) {
		Participacao participacao = mapper.fromDto(model);
		
		participacao.setEvento(eventoService.findById(model.getIdEvento()));
				
		return ResponseEntity.ok(mapper.toDto(
			participacaoService.createParticipacao(participacao)));
	}

	@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		participacaoService.deleteById(id);
		 return ResponseEntity.ok("ISOLADOS!!!");
    }

}