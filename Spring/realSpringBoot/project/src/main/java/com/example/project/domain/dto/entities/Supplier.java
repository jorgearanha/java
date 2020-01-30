package com.example.project.domain.dto.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CompanyName", nullable = false, length = 40, columnDefinition = "nvarchar")
    private String companyName;

    @Column(name = "ContactName", nullable = false, length = 50, columnDefinition = "nvarchar")
    private String contactName;    
    
    @Column(name = "ContactTitle", nullable = false, length = 40, columnDefinition = "nvarchar")
    private String contactTitle;

    @Column(name = "City", nullable = false, length = 40, columnDefinition = "nvarchar")
    private String city;

    @Column(name = "Country", nullable = false, length = 40, columnDefinition = "nvarchar")
    private String country;

    @Column(name = "Phone", nullable = false, length = 30, columnDefinition = "nvarchar")
    private String phone;

    @Column(name = "Fax", nullable = false, length = 30, columnDefinition = "nvarchar")
    private String fax;

}