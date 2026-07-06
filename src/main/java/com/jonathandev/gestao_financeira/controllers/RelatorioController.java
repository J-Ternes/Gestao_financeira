package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.RelatorioLancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto;
import com.jonathandev.gestao_financeira.services.RelatorioLancamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioLancamentoService relatorioLancamentoService;

    @GetMapping("/total/{categoria}")
    public ResponseEntity mostrarLancamentosPorCategoria(@PathVariable String categoria){
       RelatorioLancamentoResponseDto lancamentosPorCategoria =  relatorioLancamentoService.historicoDeGastoPorCategoria(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(lancamentosPorCategoria);
    }

    @GetMapping("/{categoria}")
    public ResponseEntity totalGastoPorCategoria(@PathVariable String categoria){
        ValorTotalPorCategoriaResponseDto valorTotalPorCategoriaResponseDto =  relatorioLancamentoService.totalPorCategoria(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(valorTotalPorCategoriaResponseDto);
    }
}
