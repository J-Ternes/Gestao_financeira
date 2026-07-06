package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioLancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public List<LancamentoModel> mostrarPorCategoria(String categoria){
        List<CategoriaModel> categoriaDoLancamento = lancamentoRepository.
                findByCategoria(categoria);

        if(categoriaDoLancamento == null) throw new CategoriaNotFoundException();


    }
}
