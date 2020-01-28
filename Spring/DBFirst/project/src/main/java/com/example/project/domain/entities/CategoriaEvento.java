package com.example.project.domain.entities;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CategoriaEvento {

    @Id
    @Column(name = "IdCategoriaEvento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaEvento;

    @Column(name = "NomeCategoria", nullable = false, length = 250)
    private String nomeCategoria;

}