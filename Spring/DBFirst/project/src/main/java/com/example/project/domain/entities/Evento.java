package com.example.project.domain.entities;

import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Evento {

    @Id
    @Column(name = "IdEvento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @ManyToOne
    @JoinColumn(name = "IdCategoriaEvento", nullable = false)
    private CategoriaEvento categoriaEvento;

    @ManyToOne
    @JoinColumn(name = "IdEventoStatus", nullable = false)
    private  StatusEvento statusEvento;

    @Column(name = "Nome", nullable = false, length = 250)
    private String nome;

    @Column(name = "DataHoraInicio", nullable = false)
    private Date dataHoraInicio;

    @Column(name = "DataHoraFim", nullable = false)
    private Date dataHoraFim;

    @Column(name = "Local", nullable = false, length = 250)
    private String local;

    @Column(name = "Descricao", nullable = false, length = 1000)
    private String descricao;

    @Column(name = "LimiteVagas", nullable = false)
    private Integer limiteVagas;

}