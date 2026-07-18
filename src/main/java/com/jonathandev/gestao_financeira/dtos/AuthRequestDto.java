package com.jonathandev.gestao_financeira.dtos;

public record AuthRequestDto(
        String email,
        String senha
) {
}
