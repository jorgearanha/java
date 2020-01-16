package com.example.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.response.EventoResponse;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.mapper.EventoMapper;
import com.example.project.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/evento")
public class EventoController {

	private final EventoService eventoService;
	private final EventoMapper mapper;

	@Autowired
	public EventoController(EventoService eventoService, EventoMapper eventoMapper) {
		this.eventoService = eventoService;
		this.mapper = eventoMapper;
	}

	@GetMapping(value = "/{id}")
    public ResponseEntity<EventoResponse> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(mapper.toDto(eventoService.findById(id))) ;
    }
	
	@GetMapping
    public ResponseEntity<List<EventoResponse>> list() {
		return ResponseEntity.ok(eventoService.listClient().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<EventoResponse> post(@RequestBody EventoCreateRequest model) {
		Evento evento = mapper.fromDto(model);
		return ResponseEntity.ok(mapper.toDto(
			eventoService.createEvento(evento, model.getIdCategoriaEvento(), model.getIdEventoStatus())));
	}

}