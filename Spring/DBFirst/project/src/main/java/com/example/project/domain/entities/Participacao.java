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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Participacao {

    @Id
    @Column(name = "IdParticipacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParticipacao;

    @ManyToOne
    @JoinColumn(name = "IdEvento", nullable = false)
    private Evento evento;

    @Column(name = "LoginParticipante", nullable = false, length = 250)
    private String loginParticipante;

    @Column(name = "FlagPresente", nullable = false)
    private Boolean flagPresente;

    @Column(name = "Nota", nullable = true)
    private Integer nota;

    @Column(name = "Comentario", nullable = true, length = 1000)
    private String comentario;

}