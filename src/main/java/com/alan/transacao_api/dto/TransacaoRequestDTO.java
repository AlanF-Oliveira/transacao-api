package com.alan.transacao_api.dto;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
        Double valor,
        OffsetDateTime dataHora
) {
}
