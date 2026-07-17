package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.dtos.RelatorioLancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto;
import com.jonathandev.gestao_financeira.services.RelatorioLancamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioLancamentoService relatorioLancamentoService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{categoria}")
    public ResponseEntity<PaginaResponseDto<RelatorioLancamentoResponseDto>> mostrarLancamentosPorCategoria(@PathVariable String categoria,
                                                                            @RequestParam(defaultValue = "0") int pagina,
                                                                            @RequestParam(defaultValue = "10") int tamanho,
                                                                            @RequestParam(defaultValue = "dataLancamento") String ordenarPor){

       PaginaResponseDto lancamentosPorCategoria =  relatorioLancamentoService.historicoDeGastoPorCategoria(categoria,pagina,tamanho,ordenarPor);

        return ResponseEntity.status(HttpStatus.OK).body(lancamentosPorCategoria);
    }

    @GetMapping("/total/{categoria}")
    public ResponseEntity totalGastoPorCategoria(@PathVariable String categoria){
        ValorTotalPorCategoriaResponseDto valorTotalPorCategoriaResponseDto =  relatorioLancamentoService.calcularTotalPorCategoria(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(valorTotalPorCategoriaResponseDto);
    }
}
