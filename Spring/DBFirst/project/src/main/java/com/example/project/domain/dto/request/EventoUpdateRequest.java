package com.example.project.domain.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoUpdateRequest {

    private Integer IdEventoStatus;

    @NotEmpty(message = "Local is required")
    private String Local;

    @NotEmpty(message = "Descricao is required")
    private String Descricao;

    @NotEmpty(message = "LimiteVagas is required")
    private Integer LimiteVagas;

}