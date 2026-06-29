package com.alan.transacao_api.controller;

import com.alan.transacao_api.business.service.EstatisticasService;
import com.alan.transacao_api.controller.dto.EstatisticasResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    public ResponseEntity<EstatisticasResponseDTO> calcularEstatisticasDeTransacoes(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasDeTransacoes(intervaloBusca));
    }
}
