package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.dtos.LancamentoResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.exceptions.LancamentoNotFoundException;
import com.jonathandev.gestao_financeira.exceptions.UserNotFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.repositories.CategoriaRepository;
import com.jonathandev.gestao_financeira.repositories.LancamentoRepository;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final UserRepository userRepository;
    private final CategoriaRepository categoriaRepository;

    public LancamentoModel cadastrarLancamento(LancamentoRequestDto requestDto){

        UserModel usuario = userRepository
                .findById(requestDto.usuarioId())
                .orElseThrow(()->new UserNotFoundException());

        CategoriaModel categoria = categoriaRepository
                .findById(requestDto.categoriaId())
                .orElseThrow(()-> new CategoriaNotFoundException());

        LancamentoModel novoLancamento = new LancamentoModel();
        novoLancamento.setCategoria(categoria);
        novoLancamento.setDataLancamento(requestDto.dataLancamento());
        novoLancamento.setUsuario(usuario);
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
