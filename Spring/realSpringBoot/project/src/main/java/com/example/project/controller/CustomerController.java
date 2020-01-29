package com.example.project.controller;

import java.util.List;

import com.example.project.domain.Customer;
import com.example.project.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/list")
	public ResponseEntity<List<Customer>> list() {
		return ResponseEntity.ok(customerService.list());
	}

	@GetMapping(value = "id={id}")
	public ResponseEntity<Customer> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(customerService.findById(id));
	}
}