package com.jonathandev.gestao_financeira.dtos;
import com.jonathandev.gestao_financeira.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
public record UserRequestDto(

@NotBlank(message = "O campo nome é obrigatório")
String nome,

@NotBlank(message = "O campo email é obrigatório")
@Email(message = "Email inválido")
String email,

@NotBlank(message = "O campo senha é obrigatório")
String senha,

@NotNull(message = "O campo role é obrigatório")
UserRole role

)
{ }
