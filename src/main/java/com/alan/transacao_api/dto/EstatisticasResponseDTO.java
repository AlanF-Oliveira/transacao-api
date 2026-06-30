package com.alan.transacao_api.dto;

public record EstatisticasResponseDTO(
        Long count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
