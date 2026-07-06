package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.LancamentoResumoDto;
import com.jonathandev.gestao_financeira.dtos.RelatorioLancamentoResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioLancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public RelatorioLancamentoResponseDto mostrarPorCategoria(String nomeCategoria){

        List<LancamentoModel> lancamentos = lancamentoRepository.findByCategoriaNome(nomeCategoria);

        if(lancamentos.isEmpty()) throw new CategoriaNotFoundException();

        BigDecimal totalGasto = lancamentos
                .stream()
                .map(LancamentoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<LancamentoResumoDto> resumo = lancamentos
                .stream()
                .map(l -> new LancamentoResumoDto(
                        l.getPreco(),
                        l.getDataLancamento()
                ))
                .toList();

        return new RelatorioLancamentoResponseDto(nomeCategoria, totalGasto, resumo);
    }
}
