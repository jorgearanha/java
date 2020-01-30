package com.example.project.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private CustomerOrder customerOrder;    
    
    @ManyToOne
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;    

    @Column(name = "UnitPrice", nullable = false, columnDefinition = "decimal")
    private Double unitPrice;

    @Column(name = "Quantity", nullable = false, length = 30)
    private Integer quantity;
    
}