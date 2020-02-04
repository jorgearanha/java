package com.example.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.project.util.MyDateUtil.montaDate;

import com.example.project.domain.dto.request.EventoCreateRequest;
import com.example.project.domain.dto.request.EventoUpdateRequest;
import com.example.project.domain.dto.response.EventoResponse;
import com.example.project.domain.entities.CategoriaEvento;
import com.example.project.domain.entities.Evento;
import com.example.project.domain.mapper.EventoMapper;
import com.example.project.service.CategoriaEventoService;
import com.example.project.service.EventoService;
import com.example.project.service.StatusEventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private EventoService eventoService;
	@Autowired
	private CategoriaEventoService categoriaEventoService;
	@Autowired
	private StatusEventoService statusEventoService;
	@Autowired
	private EventoMapper mapper;

	@GetMapping(value = "/{id}")
    public ResponseEntity<EventoResponse> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(mapper.toDto(eventoService.findById(id))) ;
    }
	
	@GetMapping
    public ResponseEntity<List<EventoResponse>> list() {
		return ResponseEntity.ok(eventoService.list().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/status_aberto")
    public ResponseEntity<List<EventoResponse>> listStatusAberto() {
		return ResponseEntity.ok(eventoService.listStatusAberto().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/categoria={id}")
    public ResponseEntity<List<EventoResponse>> listByCategoria(@PathVariable Integer id) {
		CategoriaEvento categoriaEvento = categoriaEventoService.findById(id);
		return ResponseEntity.ok(eventoService.listByCategoria(categoriaEvento).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "/data/{dia}/{mes}/{ano}")
    public ResponseEntity<List<EventoResponse>> listByDate(@PathVariable Integer dia, @PathVariable Integer mes, @PathVariable Integer ano) {
		System.out.println(montaDate(dia, mes, ano));
		return ResponseEntity.ok(eventoService.listByDate(montaDate(dia, mes, ano)).stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList())); 
    }

	@PostMapping
	public ResponseEntity<EventoResponse> post(@RequestBody EventoCreateRequest model) {
		Evento evento = mapper.fromDto(model);
		
		evento.setCategoriaEvento(
			categoriaEventoService.findById(model.getIdCategoriaEvento())
		);

		evento.setStatusEvento(
			statusEventoService.findById(model.getIdEventoStatus())
		);
		Evento e = eventoService.createEvento(evento);
				
		return ResponseEntity.ok(mapper.toDto(e));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		eventoService.deleteById(id);
		return ResponseEntity.ok("ISOLADOS!!!");
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody EventoUpdateRequest evento) {
		Evento model = mapper.fromUDto(evento);
		Evento e = eventoService.putEvento(id, model);
		return ResponseEntity.ok(mapper.toDto(e));
	}

	@PutMapping(value = "cancela/{id}")
	public ResponseEntity<?> cancela(@PathVariable Integer id) {
	    Evento e = eventoService.cancelaEvento(id);
		return ResponseEntity.ok(mapper.toDto(e));
	}

	
}