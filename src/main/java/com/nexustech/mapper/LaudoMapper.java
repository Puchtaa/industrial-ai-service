package com.nexustech.mapper;

import com.nexustech.dto.LaudoTecnicoDTO;
import com.nexustech.entity.LaudoTecnicoEntity;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Set;

public final class LaudoMapper {

    private static final Set<String> GRAVIDADES_VALIDAS =
            Set.of("BAIXA", "MEDIA", "ALTA", "CRITICA");

    private LaudoMapper() {
    }

    public static LaudoTecnicoEntity toEntity(
            LaudoTecnicoDTO dto
    ) {
        if (dto == null) {
            throw new IllegalArgumentException(
                    "O laudo técnico não pode ser nulo."
            );
        }

        LaudoTecnicoEntity entity = new LaudoTecnicoEntity();

        entity.setEquipamento(
                textoObrigatorio(dto.equipamento(), "equipamento")
        );

        entity.setResumoFalha(
                textoObrigatorio(dto.resumoFalha(), "resumoFalha")
        );

        entity.setGravidade(
                normalizarGravidade(dto.gravidade())
        );

        entity.setAcoesRecomendadas(
                dto.acoesRecomendadas()
        );

        entity.setPararProducao(
                dto.pararProducao()
        );

        return entity;
    }

    private static String textoObrigatorio(
            String valor,
            String campo
    ) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "O campo " + campo + " é obrigatório."
            );
        }

        return valor.trim();
    }

    private static String normalizarGravidade(String valor) {
        String gravidade = textoObrigatorio(
                valor,
                "gravidade"
        );

        gravidade = Normalizer
                .normalize(gravidade, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toUpperCase(Locale.ROOT);

        if (!GRAVIDADES_VALIDAS.contains(gravidade)) {
            throw new IllegalArgumentException(
                    "Gravidade inválida: " + valor
            );
        }

        return gravidade;
    }
}