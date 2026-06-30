package com.alan.transacao_api.service;

import com.alan.transacao_api.dto.EstatisticasResponseDTO;
import com.alan.transacao_api.dto.TransacaoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {

    @InjectMocks
    EstatisticasService estatisticasService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;
    EstatisticasResponseDTO estatisticas;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDTO(50.5, OffsetDateTime.now());
        estatisticas = new EstatisticasResponseDTO(1L, 50.5, 50.5, 50.5, 50.5);
    }

    @Test
    void calcularEstatisticasComSucesso() {
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.singletonList(transacao));
        EstatisticasResponseDTO resultado = estatisticasService.calcularEstatisticasDeTransacoes(60);
        verify(transacaoService, times(1)).buscarTransacoes(60);
        assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
    }

    @Test
    void calcularEstatisticasQuandoListaVazia() {
        EstatisticasResponseDTO estatisticaEsperado = new EstatisticasResponseDTO(0L, 0.0,0.0,0.0,0.0);
        when(transacaoService.buscarTransacoes(60)).thenReturn(Collections.emptyList());
        EstatisticasResponseDTO resultado = estatisticasService.calcularEstatisticasDeTransacoes(60);
        verify(transacaoService, times(1)).buscarTransacoes(60);
        assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticaEsperado);

    }
}