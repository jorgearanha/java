package com.example.project.domain.dto.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotNull(message = "Invalid DataHoraInicio")
    private Date DataHoraInicio;

    @NotNull(message = "Invalid DataHoraFim")
    private Date DataHoraFim;

    @NotEmpty(message = "LimiteVagas is required")
    private Integer LimiteVagas;

}