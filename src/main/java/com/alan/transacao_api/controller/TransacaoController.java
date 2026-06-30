package com.alan.transacao_api.controller;

import com.alan.transacao_api.service.TransacaoService;
import com.alan.transacao_api.dto.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "endpoint responsável por adiciona transacões")
    @ApiResponse(responseCode = "201", description = "Transacão gravada com sucesso")
    @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transacão")
    @ApiResponse(responseCode = "400", description = "Erro de requisicão")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "endpoint responsável por deletar transacões")
    @ApiResponse(responseCode = "204", description = "Transacões deletadas com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de requisicão")
    @ApiResponse(responseCode = "500", description = "Erro interno")
    public ResponseEntity<Void> limparTransacoes(){
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
