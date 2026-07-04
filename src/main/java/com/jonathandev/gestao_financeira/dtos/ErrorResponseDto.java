package com.jonathandev.gestao_financeira.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        int status,
        String mensagem,
        LocalDateTime timestamp
) {
}
