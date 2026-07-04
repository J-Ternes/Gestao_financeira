package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.UserRole;

public record UserResponseDto(
        String nome,
        String email,
        UserRole role
) {
}
