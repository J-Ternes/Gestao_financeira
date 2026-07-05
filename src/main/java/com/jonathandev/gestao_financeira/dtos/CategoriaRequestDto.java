package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.UserModel;

import java.util.UUID;

public record CategoriaRequestDto(
        String categoria,
        UUID usuarioId
) {
}
