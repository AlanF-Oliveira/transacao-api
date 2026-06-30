package com.alan.transacao_api.service;

import com.alan.transacao_api.dto.EstatisticasResponseDTO;
import com.alan.transacao_api.dto.TransacaoRequestDTO;
import com.alan.transacao_api.exception.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @InjectMocks
    TransacaoService transacaoService;

    @Mock
    EstatisticasService estatisticasService;

    TransacaoRequestDTO transacao;
    EstatisticasResponseDTO estatisticas;
    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(50.5, OffsetDateTime.now());
        estatisticas = new EstatisticasResponseDTO(1L, 50.5, 50.5, 50.5, 50.5);
    }

    @Test
    void deveAdicionarTransacoesComSuscesso(){
        transacaoService.adicionarTransacoes(transacao);
        List<TransacaoRequestDTO> transacoes  = transacaoService.buscarTransacoes(5000);
        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLancarExcecaoCasoValorSejaNegativo(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));
        assertEquals("Valor não pode ser menor que 0", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoCasoDataEHoraMaiorQueAtual(){
        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacoes(new TransacaoRequestDTO(10.1, OffsetDateTime.now().plusDays(1))));
        assertEquals("Data e hora maiores que a data e hora atuais", exception.getMessage());
    }

    @Test
    void deveLimparTransacoesComSuscesso(){
        transacaoService.limparTransacoes();
        List<TransacaoRequestDTO> transacoes  = transacaoService.buscarTransacoes(5000);
        assertTrue(transacoes.isEmpty());
    }

    @Test
    void deveBuscarTransacoesDentroDoIntervalo(){
        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));
        transacaoService.adicionarTransacoes(transacao);
        transacaoService.adicionarTransacoes(dto);
        List<TransacaoRequestDTO> transacoes  = transacaoService.buscarTransacoes(60);
        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));

    }
}
