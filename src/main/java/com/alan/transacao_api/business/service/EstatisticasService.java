package com.alan.transacao_api.business.service;

import com.alan.transacao_api.controller.dto.EstatisticasResponseDTO;
import com.alan.transacao_api.controller.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {
    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervaloDeBusca){
        log.info("Iniciada busca de estatísticas  de transações pelo periodo de tempo " + intervaloDeBusca);
        List<TransacaoRequestDTO> transacoes =  transacaoService.buscarTransacoes(intervaloDeBusca);

        if (transacoes.isEmpty()){
            return new EstatisticasResponseDTO(0L,0.0,0.0,0.0,0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticasResponseDTO(estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
