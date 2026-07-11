package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.*;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.repositories.CategoriaRepository;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioLancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;

    public ValorTotalPorCategoriaResponseDto calcularTotalPorCategoria(String nomeCategoria){

        CategoriaModel verificandoCategoria = categoriaRepository.findByCategoria(nomeCategoria);

       if(verificandoCategoria == null) throw new CategoriaNotFoundException();

        BigDecimal totalGasto = lancamentoRepository.calcularTotalPorCategoria(nomeCategoria);

        return new ValorTotalPorCategoriaResponseDto(nomeCategoria,totalGasto);
    }


    public PaginaResponseDto<RelatorioLancamentoResponseDto> historicoDeGastoPorCategoria(String nomeCategoria, int pagina, int tamanho, String ordenarPor){

        CategoriaModel categoria = lancamentoRepository.findByCategoria(nomeCategoria);

        if(categoria == null) throw new CategoriaNotFoundException();

        Pageable pageable = PageRequest.of(pagina,tamanho, Sort.by(Sort.Direction.DESC,ordenarPor));

         Page<LancamentoModel> page = lancamentoRepository.findByCategoriaNome(nomeCategoria, pageable);


        List<LancamentoResponseDto> conteudo = page.getContent()
                .stream()
                .map(lancamento-> new LancamentoResponseDto(
                        lancamento.getPreco(),
                        lancamento.getDataLancamento(),
                        lancamento.getTipo(),
                        lancamento.getCategoria().getCategoria()
                )).toList();

        return new PaginaResponseDto(conteudo,page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }
}
