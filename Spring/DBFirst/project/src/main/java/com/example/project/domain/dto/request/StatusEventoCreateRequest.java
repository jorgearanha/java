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
public class StatusEventoCreateRequest {

    @NotEmpty(message = "name is required")
    private String NomeStatus;

}