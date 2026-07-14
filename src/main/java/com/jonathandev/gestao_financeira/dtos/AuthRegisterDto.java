package com.jonathandev.gestao_financeira.dtos;

import com.jonathandev.gestao_financeira.model.UserRole;

public record AuthRegisterDto(String email, String senha, UserRole role) {
}
