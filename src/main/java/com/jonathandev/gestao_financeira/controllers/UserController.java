package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.UserRequestDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cadastrados")
    public ResponseEntity usuariosCadastrados(){
    List<UserResponseDto> usuarios = userService.usuariosCadastrados();

    return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletarUsuario(@PathVariable UUID id){
    userService.deletar(id);
    return ResponseEntity.noContent().build();
    }


}
