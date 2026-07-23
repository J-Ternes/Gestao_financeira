package com.jonathandev.gestao_financeira.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriaResponseDto(
        @NotBlank (message = "Campo categoria vazio")
        String categoria
) {
}
