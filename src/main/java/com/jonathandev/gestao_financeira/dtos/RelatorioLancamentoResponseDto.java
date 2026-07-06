package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;

public record RelatorioLancamentoResponseDto(
        String categoria,
        BigDecimal total
) {
}
