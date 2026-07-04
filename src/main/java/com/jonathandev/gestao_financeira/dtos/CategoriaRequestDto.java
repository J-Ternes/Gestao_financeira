package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.UserModel;

public record CategoriaRequestDto(
        String categoria,
        UserModel usuario
) {
}
