package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.dtos.LancamentoResponseDto;
import com.jonathandev.gestao_financeira.exceptions.LancamentoNotFoundException;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<LancamentoResponseDto> todosLancamentos(){
        List<LancamentoModel> todosLancamentos = lancamentoRepository.findAll();

        List<LancamentoResponseDto> respondeDto = todosLancamentos
                .stream()
                .map(lancamentos-> new LancamentoResponseDto(
                lancamentos.getPreco(),
                lancamentos.getDataLancamento(),
                lancamentos.getTipo(),
                lancamentos.getCategoria()
        )).toList();

        return respondeDto;
    }

    public void deletar(UUID id){
        LancamentoModel lancamento  = lancamentoRepository.findById(id).orElseThrow(()-> new LancamentoNotFoundException());

        lancamentoRepository.delete(lancamento);
    }
}
