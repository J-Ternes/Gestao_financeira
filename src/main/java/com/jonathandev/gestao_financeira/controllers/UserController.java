package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.UserRequestDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/cadastro")
    public ResponseEntity novoUsuario(@RequestBody @Valid UserRequestDto userRequestDTO) {
        UserModel response = userService.cadastrar(userRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cadastrados")
    public ResponseEntity usuariosCadastrados(){
    List<UserResponseDto> usuarios = userService.usuariosCadastrados();

    return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }


}
