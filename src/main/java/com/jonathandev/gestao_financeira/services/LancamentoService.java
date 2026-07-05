package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoModel cadastrarLancamento(LancamentoRequestDto requestDto){

        LancamentoModel novoLancamento = new LancamentoModel();
        novoLancamento.setCategoria(requestDto.categoria());
        novoLancamento.setDataLancamento(requestDto.dataLancamento());
        novoLancamento.setUsuario(requestDto.usuario());
        novoLancamento.setPreco(requestDto.preco());
        novoLancamento.setTipo(requestDto.tipo());

        return lancamentoRepository.save(novoLancamento);
    }
}
