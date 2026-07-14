package com.jonathandev.gestao_financeira.auth;

public record AuthRequestDto(
        String email,
        String senha
) {
}
