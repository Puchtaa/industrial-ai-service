package com.nexustech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record LaudoTecnicoDTO(

        @NotBlank
        String equipamento,

        @NotBlank
        String resumoFalha,

        @NotBlank
        String gravidade,

        @NotEmpty
        List<@NotBlank String> acoesRecomendadas,

        boolean pararProducao

) {
}