package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.LancamentoResumoDto;
import com.jonathandev.gestao_financeira.dtos.RelatorioLancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.ValorTotalPorCategoriaResponseDto;
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

    public ValorTotalPorCategoriaResponseDto totalPorCategoria(String nomeCategoria){

       if(lancamentoRepository.findByCategoria(nomeCategoria) == null) throw new CategoriaNotFoundException();

        BigDecimal totalGasto = lancamentoRepository.calcularTotalPorCategoria(nomeCategoria);

        return new ValorTotalPorCategoriaResponseDto(totalGasto);
    }


    public RelatorioLancamentoResponseDto historicoDeGastoPorCategoria(String nomeCategoria){
         List<LancamentoModel> lancamentos = lancamentoRepository.findByCategoriaNome(nomeCategoria);

         if(lancamentos.isEmpty()) throw new CategoriaNotFoundException();

          List<LancamentoResumoDto> resumo = lancamentos
                .stream()
                .map(l -> new LancamentoResumoDto(
                        l.getPreco(),
                        l.getDataLancamento()
                ))
                .toList();

        return new RelatorioLancamentoResponseDto(nomeCategoria, resumo);
    }
}
