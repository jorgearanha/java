package com.example.project.repository;

import com.example.project.domain.dto.entities.CustomerOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer>{
}