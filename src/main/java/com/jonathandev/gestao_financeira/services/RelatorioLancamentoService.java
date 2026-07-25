package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.constants.PaginacaoConstantes;
import com.jonathandev.gestao_financeira.dtos.*;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.helpers.Helpers;
import com.jonathandev.gestao_financeira.helpers.PaginacaoUtils;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.model.UserModel;
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
    private final Helpers helpers;

    public ValorTotalPorCategoriaResponseDto calcularTotalPorCategoria(String nomeCategoria){

        UserModel usuario = helpers.getUsuarioAutenticado();

        CategoriaModel categoria = categoriaRepository.findByCategoriaAndUsuarioId(nomeCategoria, usuario.getId());

       if(categoria == null) throw new CategoriaNotFoundException();

        BigDecimal totalGasto = lancamentoRepository.calcularTotalPorCategoriaAndUsuario(nomeCategoria,usuario.getId());

        return new ValorTotalPorCategoriaResponseDto(nomeCategoria,totalGasto);
    }


    public PaginaResponseDto<RelatorioLancamentoResponseDto> historicoDeGastoPorCategoria(String nomeCategoria, int pagina, String ordenarPor){

        PaginacaoUtils.validarNumeroPaginas(pagina);

        UserModel usuario = helpers.getUsuarioAutenticado();

        CategoriaModel categoria = categoriaRepository.findByCategoriaAndUsuarioId(nomeCategoria, usuario.getId());

        if(categoria == null) throw new CategoriaNotFoundException();

        Pageable pageable = PageRequest.of(pagina, PaginacaoConstantes.TAMANHO_PAGINA, Sort.by(Sort.Direction.ASC,ordenarPor));

         Page<LancamentoModel> page = lancamentoRepository.findByCategoriaAndUsuarioNomePaginado(nomeCategoria, usuario.getId(), pageable);

        //Crio o resumo de cada lancamento com o preco e a data
        List<LancamentoResumoDto> resumo = page.getContent()
                .stream()
                .map(lancamento-> new LancamentoResumoDto(
                        lancamento.getPreco(),
                        lancamento.getDataLancamento()
                )).toList();

        //Crio o relatorio dos lancamentos de cada categoria
        List<RelatorioLancamentoResponseDto> conteudo = List.of(
                new RelatorioLancamentoResponseDto(nomeCategoria, resumo)
        );

        return new PaginaResponseDto(conteudo,page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }
}
