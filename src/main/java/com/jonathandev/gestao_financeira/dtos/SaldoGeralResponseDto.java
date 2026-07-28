package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;

public record SaldoGeralResponseDto(
        BigDecimal totalReceitas,
        BigDecimal totalDespesas,
        BigDecimal totalInvestimentos,
        BigDecimal saldoFinal
) {
}
