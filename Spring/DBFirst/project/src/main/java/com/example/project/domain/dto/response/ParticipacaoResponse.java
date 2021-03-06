package com.example.project.domain.dto.response;

import com.example.project.domain.entities.Evento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacaoResponse {

    private Integer IdParticipacao;
    private Evento evento;
    private String LoginParticipante;
    private Boolean FlagPresente;
    private Integer Nota;
    private String Comentario;

}