package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.services.RelatorioLancamentoService;
import com.jonathandev.gestao_financeira.services.LancamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lancamento")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @PostMapping("/novo")
    public ResponseEntity novoLancamento(@RequestBody @Valid LancamentoRequestDto lancamentoDto){
        LancamentoModel lancamento = lancamentoService.cadastrarLancamento(lancamentoDto);

        return ResponseEntity.status(HttpStatus.OK).body(lancamento);
    }

    @GetMapping("/cadastrados")
    public ResponseEntity mostrarTodosLancamentos(){

        return ResponseEntity.status(HttpStatus.OK).body(lancamentoService.todosLancamentos());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarLancamento(@PathVariable UUID id){
        lancamentoService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
