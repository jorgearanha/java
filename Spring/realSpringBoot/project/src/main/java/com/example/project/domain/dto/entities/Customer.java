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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40, columnDefinition = "nvarchar")
    private String firstName;

    @Column(nullable = false, length = 40, columnDefinition = "nvarchar")
    private String lastName;

    @Column(nullable = false, length = 40, columnDefinition = "nvarchar")
    private String city;

    @Column(nullable = false, length = 40, columnDefinition = "nvarchar")
    private String country;

    @Column(nullable = false, length = 40, columnDefinition = "nvarchar")
    private String phone;
}