package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.dtos.RelatorioLancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.SaldoGeralResponseDto;
import com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto;
import com.jonathandev.gestao_financeira.services.RelatorioLancamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioLancamentoService relatorioLancamentoService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{categoria}")
    public ResponseEntity<PaginaResponseDto<RelatorioLancamentoResponseDto>> mostrarLancamentosPorCategoria(@PathVariable String categoria,
                                                                            @RequestParam(defaultValue = "0") int pagina,
                                                                            @RequestParam(defaultValue = "dataLancamento") String ordenarPor){

       PaginaResponseDto lancamentosPorCategoria =  relatorioLancamentoService.historicoDeGastoPorCategoria(categoria,pagina,ordenarPor);

        return ResponseEntity.status(HttpStatus.OK).body(lancamentosPorCategoria);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/total/{categoria}")
    public ResponseEntity<ValorTotalPorCategoriaResponseDto> totalGastoPorCategoria(@PathVariable String categoria){
        ValorTotalPorCategoriaResponseDto valorTotalPorCategoriaResponseDto =  relatorioLancamentoService.calcularTotalPorCategoria(categoria);

        return ResponseEntity.status(HttpStatus.OK).body(valorTotalPorCategoriaResponseDto);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/total")
    public ResponseEntity<List<ValorTotalPorCategoriaResponseDto>> totalGastoTodasCategorias() {
        List<ValorTotalPorCategoriaResponseDto> totais = relatorioLancamentoService.calcularTotalDeTodasCategorias();

        return ResponseEntity.status(HttpStatus.OK).body(totais);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/saldo")
    public ResponseEntity<SaldoGeralResponseDto> obterSaldoGeral() {

        SaldoGeralResponseDto saldo = relatorioLancamentoService.calcularSaldoGeral();

        return ResponseEntity.status(HttpStatus.OK).body(saldo);
    }
}
