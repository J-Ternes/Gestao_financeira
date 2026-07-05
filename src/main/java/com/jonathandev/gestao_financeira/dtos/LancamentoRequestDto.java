package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.TipoLancamento;
import com.jonathandev.gestao_financeira.model.UserModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LancamentoRequestDto(
        BigDecimal preco,
        LocalDate dataLancamento,
        TipoLancamento tipo,
        UUID categoriaId,
        UUID usuarioId
) {
}
