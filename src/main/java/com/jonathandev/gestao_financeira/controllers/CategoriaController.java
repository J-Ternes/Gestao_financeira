package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.dtos.CategoriaResponseDto;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/inserir")
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){
        CategoriaModel categoriaCriada = categoriaService.criarCategoria(categoriaDto);

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCriada);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/cadastradas")
    public ResponseEntity categoriasCadastradas(){
        List<CategoriaResponseDto> categorias = categoriaService.buscarTodasCategorias();

        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity delatarCategoria(@PathVariable UUID id){
        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
