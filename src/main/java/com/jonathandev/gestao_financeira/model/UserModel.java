package com.jonathandev.gestao_financeira.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank (message = "O campo nome é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank (message = "O campo email é obrigatório")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}
