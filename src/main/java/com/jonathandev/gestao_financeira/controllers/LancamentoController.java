package com.jonathandev.gestao_financeira.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lancamento")
public class LancamentoController {

    @PostMapping("/novo")
    public ResponseEntity novoLancamento(@RequestBody @Valid LancamentoRequestDto){

    }
}
