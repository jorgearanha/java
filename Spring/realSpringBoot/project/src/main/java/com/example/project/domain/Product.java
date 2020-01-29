package com.example.project.domain;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ProductName", nullable = false, length = 50, columnDefinition = "nvarchar")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "SupplierId", nullable = false)
    private Supplier supplier;    
    
    @Column(name = "UnitPrice", columnDefinition = "decimal")
    private Double unitPrice;

    @Column(name = "Package", length = 30, columnDefinition = "nvarchar")
    private String packageName;

    @Column(name = "IsDiscontinued", nullable = false)
    private Boolean isDiscontinued;
    
    @Column(name = "Image", length = 400, columnDefinition = "nvarchar")
    private String image;
}