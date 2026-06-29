package com.alan.transacao_api.business.service;

import com.alan.transacao_api.controller.dto.EstatisticasResponseDTO;
import com.alan.transacao_api.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {
    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervaloDeBusca){
        List<TransacaoRequestDTO> transacoes =  transacaoService.buscarTransacoes(intervaloDeBusca);
        DoubleSummaryStatistics estatisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticasResponseDTO(estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
