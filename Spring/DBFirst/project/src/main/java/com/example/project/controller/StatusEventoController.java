package com.example.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.project.domain.dto.response.StatusEventoResponse;
import com.example.project.domain.mapper.StatusEventoMapper;
import com.example.project.service.StatusEventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status-evento")
public class StatusEventoController {

	private final StatusEventoService statusEventoService;
	private final StatusEventoMapper mapper;

	@Autowired
	public StatusEventoController(StatusEventoService statusEventoService, StatusEventoMapper statusEventoMapper) {
		this.statusEventoService = statusEventoService;
		this.mapper = statusEventoMapper;
	}

	@GetMapping(value = "/{id}")
    public ResponseEntity<StatusEventoResponse> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(mapper.toDto(statusEventoService.findById(id))) ;
    }
	
	@GetMapping
    public ResponseEntity<List<StatusEventoResponse>> list() {
		return ResponseEntity.ok(statusEventoService.list().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

}