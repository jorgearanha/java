package com.example.project.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BashExcelResponse
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BashExcelResponse {
    private String linhas;
    private String data;
    private String arquivo;
}