package com.jonathandev.gestao_financeira.controllers;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.services.LancamentoService;
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
@RequestMapping("/lancamento")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    @PostMapping("/novo")
    public ResponseEntity novoLancamento(@RequestBody @Valid LancamentoRequestDto lancamentoDto){
        LancamentoModel lancamento = lancamentoService.cadastrarLancamento(lancamentoDto);

        return ResponseEntity.status(HttpStatus.OK).body(lancamento);
    }

}
