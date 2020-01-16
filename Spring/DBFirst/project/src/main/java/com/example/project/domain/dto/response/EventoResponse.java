package com.example.project.domain.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoResponse {

    private Integer IdCategoriaEvento;
    private Integer IdEventoStatus;
    private String Nome;
    private Date DataHoraInicio;
    private Date DataHoraFim;
    private String Local;
    private String Descricao;
    private Integer LimiteVagas;
    
}