package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;

public record ValorTotalPorCategoriaResponseDto(
        String categoria,
        BigDecimal totalGastoNaCategoria
) {
}
