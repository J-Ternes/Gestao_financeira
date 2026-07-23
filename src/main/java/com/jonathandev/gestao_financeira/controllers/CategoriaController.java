package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.dtos.CategoriaResponseDto;
import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/inserir")
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid CategoriaRequestDto categoriaDto){

        CategoriaResponseDto categoriaCriada = categoriaService.criarCategoria(categoriaDto);

        return ResponseEntity.status(HttpStatus.OK).body(categoriaCriada);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/cadastradas")
    public ResponseEntity<PaginaResponseDto<CategoriaResponseDto>> categoriasPaginadas(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int tamanho,
                                                                                       @RequestParam(defaultValue = "dataLancamento") String ordenarPor){

        PaginaResponseDto<CategoriaResponseDto> categorias = categoriaService.buscarTodasCategorias(pagina,tamanho,ordenarPor);

        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity delatarCategoria(@PathVariable UUID id){
        categoriaService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
