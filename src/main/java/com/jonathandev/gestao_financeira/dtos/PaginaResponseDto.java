package com.jonathandev.gestao_financeira.dtos;

import java.util.List;

public record PaginaResponseDto(
        List conteudo,
        int paginaAtual,
        int totalElementos,
        int totalPaginas
) {
}
