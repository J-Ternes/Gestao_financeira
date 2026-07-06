package com.jonathandev.gestao_financeira.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResumoDto(
        BigDecimal preco,
        LocalDate dataLancamento
) {
}
