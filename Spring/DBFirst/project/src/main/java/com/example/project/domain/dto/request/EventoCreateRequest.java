package com.example.project.domain.dto.request;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.project.domain.validators.MyDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoCreateRequest {

//    @NotNull(message = "Invalid IdCategoriaEvento")
    private Integer IdCategoriaEvento;

//    @NotNull(message = "Invalid IdEventoStatus")
    private Integer IdEventoStatus;

    @NotEmpty(message = "Nome is required")
    private String Nome;

    @NotNull(message = "Invalid DataHoraInicio")
    @MyDate
    private Date DataHoraInicio;

    @NotNull(message = "Invalid DataHoraFim")
    @MyDate
    private Date DataHoraFim;

    @NotEmpty(message = "Local is required")
    private String Local;

    @NotEmpty(message = "Descricao is required")
    private String Descricao;

    @NotEmpty(message = "LimiteVagas is required")
    private Integer LimiteVagas;

}