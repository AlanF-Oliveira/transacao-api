package com.alan.transacao_api.controller;

import com.alan.transacao_api.dto.TransacaoRequestDTO;
import com.alan.transacao_api.exception.UnprocessableEntity;
import com.alan.transacao_api.service.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)

public class TransacaoControllerTest {
    @InjectMocks
    TransacaoController transacaoController;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;
    MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.of(2026, 7, 1, 4, 33, 0,0, ZoneOffset.UTC));
    }

    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception {
        doNothing().when(transacaoService).adicionarTransacoes(transacao);
        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void deveGerarExcecaoAoAdicionarTransacao() throws Exception {
        doThrow(new UnprocessableEntity("Erro de requisição")).when(transacaoService).adicionarTransacoes(transacao);
        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deveDeletarTransacoesComSucesso() throws Exception {
        doNothing().when(transacaoService).limparTransacoes();
        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}
