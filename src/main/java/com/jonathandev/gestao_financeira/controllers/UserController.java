package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.UserRequestDto;
import com.jonathandev.gestao_financeira.dtos.UserResponseDto;
import com.jonathandev.gestao_financeira.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> criar(@RequestBody @Valid UserRequestDto userRequestDTO) {
        UserResponseDto response = userService.criar(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/...")
    public ResponseEntity<List<UserResponseDto>> listarTodos() {
        List<UserResponseDto> response = userService.listarTodos();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> buscarPorId(@PathVariable UUID id) {
        UserResponseDto response = userService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable UUID id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
