package com.example.project.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.project.domain.dto.request.ClientCreateRequest;
import com.example.project.domain.dto.response.ClientResponse;
import com.example.project.domain.entities.Client;
import com.example.project.domain.mapper.ClientMapper;
import com.example.project.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;
	private final ClientMapper mapper;

	@Autowired
	public ClientController(ClientService clientService, ClientMapper clientMapper) {
		this.clientService = clientService;
		this.mapper = clientMapper;
	}

	@GetMapping(value = "/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable Integer id) {
         return ResponseEntity.ok(mapper.toDto(clientService.findById(id))) ;
    }
	
	@GetMapping
    public ResponseEntity<List<ClientResponse>> list() {
		return ResponseEntity.ok(clientService.listClient().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ClientResponse> post(@Valid @RequestBody ClientCreateRequest model) {

		Client client = clientService.createClient(mapper.fromDto(model));

		return ResponseEntity.ok(mapper.toDto(client));
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.deleteClient(id);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<ClientResponse> put(@PathVariable Integer id, @RequestBody ClientCreateRequest model) {
		
		Client client = clientService.findById(id);
		client.setName(model.getName());
		client.setPhone(model.getPhone());

		return ResponseEntity.ok(mapper.toDto(clientService.createClient(client)));
	}

}