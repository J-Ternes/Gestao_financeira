package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResponseDto(
        BigDecimal preco,
        LocalDate dataLancamento,
        TipoLancamento tipo,
        String categoria
) {
}
