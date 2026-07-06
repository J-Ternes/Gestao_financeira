package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;
import java.util.List;

public record RelatorioLancamentoResponseDto(
        String categoria,
        List<LancamentoResumoDto> lancamentos
) {
}
