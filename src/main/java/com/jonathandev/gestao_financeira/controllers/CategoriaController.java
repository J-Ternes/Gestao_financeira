package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    @PostMapping("/inserir")
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto caategoriaDto){


    }
}
