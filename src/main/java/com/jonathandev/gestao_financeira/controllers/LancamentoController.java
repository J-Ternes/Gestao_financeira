package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.dtos.LancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.services.RelatorioLancamentoService;
import com.jonathandev.gestao_financeira.services.LancamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lancamento")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping("/novo")
    public ResponseEntity novoLancamento(@RequestBody @Valid LancamentoRequestDto lancamentoDto){
        LancamentoModel lancamento = lancamentoService.cadastrarLancamento(lancamentoDto);

        return ResponseEntity.status(HttpStatus.OK).body(lancamento);
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @GetMapping("/cadastrados")
    public ResponseEntity<PaginaResponseDto<LancamentoResponseDto>> lancamentosPaginados(@RequestParam(defaultValue = "0") int pagina,
                                                                                         @RequestParam(defaultValue = "10") int tamanho,
                                                                                         @RequestParam(defaultValue = "dataLancamento") String ordenarPor){

        PaginaResponseDto<LancamentoResponseDto> lancamentos = lancamentoService.todosLancamentosPaginados(pagina,tamanho,ordenarPor);

        return ResponseEntity.status(HttpStatus.OK).body(lancamentos);
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarLancamento(@PathVariable UUID id){

        lancamentoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
