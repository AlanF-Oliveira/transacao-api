package com.alan.transacao_api.controller.dto;

public record EstatisticasResponseDTO(
        Integer count,
        Double sum,
        Double avg,
        Double min,
        Double max
) {
}
