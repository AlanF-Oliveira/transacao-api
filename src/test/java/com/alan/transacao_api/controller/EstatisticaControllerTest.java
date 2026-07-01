package com.alan.transacao_api.controller;

import com.alan.transacao_api.dto.EstatisticasResponseDTO;
import com.alan.transacao_api.service.EstatisticasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    EstatisticasController estatisticasController;

    @Mock
    EstatisticasService estatisticasService;

    EstatisticasResponseDTO estatisticas;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticasController).build();
        estatisticas = new EstatisticasResponseDTO(1L, 50.5, 50.5, 50.5, 50.5);
    }

    @Test
    void deveBuscarEstatisticasComSucesso() throws Exception {
        when(estatisticasService.calcularEstatisticasDeTransacoes(60)).thenReturn(estatisticas);
        mockMvc.perform(get("/estatistica")
                        .param("intervaloBusca", "60")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(estatisticas.count()));
    }
}
