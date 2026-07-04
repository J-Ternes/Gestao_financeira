package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping("/inserir")
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){
        CategoriaModel categoriaCriada = categoriaService.criarCategoria(categoriaDto);

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCriada);

    }
}
