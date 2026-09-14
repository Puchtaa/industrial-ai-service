package com.nexustech.dto;

public record LaudoBuscaDTO(
        String equipamento,
        String resumoFalha,
        String gravidade,
        double pontuacaoSimilaridade
) {
}