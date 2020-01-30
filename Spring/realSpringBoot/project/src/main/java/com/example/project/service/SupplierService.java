package com.example.project.service;

import java.util.List;
import java.util.Optional;

import com.example.project.domain.dto.entities.Supplier;
import com.example.project.exception.DataNotFoundException;
import com.example.project.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService{

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> list() {
        return supplierRepository.findAll();
    }

    public Supplier findById(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.orElseThrow(() -> new DataNotFoundException("Product not found"));
    }
    
}