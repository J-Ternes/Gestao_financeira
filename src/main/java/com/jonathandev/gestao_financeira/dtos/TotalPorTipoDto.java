package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.TipoLancamento;

import java.math.BigDecimal;

public record TotalPorTipoDto(
        TipoLancamento tipo,
        BigDecimal total) {
}
