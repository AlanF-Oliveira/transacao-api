package com.alan.transacao_api.controller;

import com.alan.transacao_api.service.EstatisticasService;
import com.alan.transacao_api.dto.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "endpoint responsável por buscar estatísticas de transacões")
    @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas de transacões")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<EstatisticasResponseDTO> calcularEstatisticasDeTransacoes(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasDeTransacoes(intervaloBusca));
    }
}
