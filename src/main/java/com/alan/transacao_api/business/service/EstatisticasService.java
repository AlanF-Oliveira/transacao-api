package com.alan.transacao_api.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticasService {
    public final TransacaoService transacaoService;
}
