package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.entities.Customer;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> list() {
        return customerRepository.findAll();
    }

    public Customer findById(Integer id) {
        Optional<Customer> orderItem = customerRepository.findById(id);
        return orderItem.orElseThrow(() -> new DataNotFoundException("OrderItem not found"));
    }
}