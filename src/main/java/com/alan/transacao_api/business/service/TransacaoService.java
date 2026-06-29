package com.alan.transacao_api.business.service;


import com.alan.transacao_api.controller.dto.TransacaoRequestDTO;
import com.alan.transacao_api.infrastructure.exception.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        log.info("Iniciado o processamento de gravar transações.");
        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data e hora atuais.");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atuais.");
        }
        if (dto.valor() < 0) {
            log.error("Valor não pode ser menor que 0.");
            throw new UnprocessableEntity("Valor não pode ser menor que 0.");
        }
        listaTransacoes.add(dto);
    }

    public void limparTransacoes(){
        log.info("transações apagadas.");
        listaTransacoes.clear();
    }
}
