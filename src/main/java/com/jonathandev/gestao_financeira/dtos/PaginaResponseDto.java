package com.jonathandev.gestao_financeira.dtos;

import java.util.List;

public record PaginaResponseDto<T>(
        List<T> conteudo,
        int paginaAtual,
        int tamanhoPagina,
        long totalElementos,
        int totalPaginas
) {
}
