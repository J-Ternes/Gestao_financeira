package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.auth.AuthRequestDto;
import com.jonathandev.gestao_financeira.auth.TokenService;
import com.jonathandev.gestao_financeira.dtos.AuthRegisterDto;
import com.jonathandev.gestao_financeira.dtos.AuthResponseDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;




    @PostMapping("/auth/registrar")
    public ResponseEntity registrar(@RequestBody @Valid AuthRegisterDto dados){
       userService.registrar(dados);

        return ResponseEntity.status(HttpStatus.CREATED).body("Novo usuário criado com sucesso!");
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid AuthRequestDto dados){

        UsernamePasswordAuthenticationToken emailPassword = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());

        var auth = authenticationManager.authenticate(emailPassword);

        var token = tokenService.gerarToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

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
