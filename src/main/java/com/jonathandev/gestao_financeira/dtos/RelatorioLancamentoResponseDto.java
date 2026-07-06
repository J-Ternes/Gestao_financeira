package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioLancamentoResponseDto(
        String categoria,
        BigDecimal totalGasto,
        List<LancamentoResumoDto> lancamentos
) {
}
