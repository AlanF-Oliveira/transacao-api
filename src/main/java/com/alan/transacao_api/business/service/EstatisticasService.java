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

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervaloDeBusca) {
        log.info("Iniciada busca de estatísticas  de transações pelo periodo de tempo " + intervaloDeBusca);

        long start = System.currentTimeMillis();
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloDeBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();
        long tempoRequisicao = finish - start;
        System.out.println("Tempo de requisicão: " + start + finish + " milissegundos");
        log.info("Estatisticas retornadas com sucesso");
        return new EstatisticasResponseDTO(estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax());
    }
}
