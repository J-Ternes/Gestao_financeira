package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.constants.PaginacaoConstantes;
import com.jonathandev.gestao_financeira.dtos.LancamentoRequestDto;
import com.jonathandev.gestao_financeira.dtos.LancamentoResponseDto;
import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.exceptions.IncompatibleUserException;
import com.jonathandev.gestao_financeira.exceptions.LancamentoNotFoundException;
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

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;
    private final Helpers helpers;

    public LancamentoResponseDto cadastrarLancamento(LancamentoRequestDto requestDto){

        UserModel usuario = helpers.getUsuarioAutenticado();

        CategoriaModel categoria = categoriaRepository
                .findById(requestDto.categoriaId())
                .orElseThrow(()-> new CategoriaNotFoundException());

        LancamentoModel novoLancamento = new LancamentoModel();
        novoLancamento.setCategoria(categoria);
        novoLancamento.setDataLancamento(requestDto.dataLancamento());
        novoLancamento.setUsuario(usuario);
        novoLancamento.setPreco(requestDto.preco());
        novoLancamento.setTipo(requestDto.tipo());

        LancamentoModel salvo = lancamentoRepository.save(novoLancamento);

        return new LancamentoResponseDto(salvo.getPreco(),salvo.getDataLancamento(),salvo.getTipo(),salvo.getCategoria().getCategoria());
    }

    public PaginaResponseDto<LancamentoResponseDto> todosLancamentosPaginados(int pagina, String ordenarPor){

        UserModel usuario = helpers.getUsuarioAutenticado();

        PaginacaoUtils.validarNumeroPaginas(pagina);

        Pageable pageable = PageRequest.of(pagina, PaginacaoConstantes.TAMANHO_PAGINA, Sort.by(Sort.Direction.DESC,ordenarPor));

        Page<LancamentoModel> page = lancamentoRepository.findByUsuarioId(usuario.getId(), pageable);

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

    public void deletar(UUID id){

        UserModel usuario = helpers.getUsuarioAutenticado();


        LancamentoModel lancamento  = lancamentoRepository.findById(id).orElseThrow(()-> new LancamentoNotFoundException());

        if (!lancamento.getUsuario().getId().equals(usuario.getId())) throw new IncompatibleUserException();


        lancamentoRepository.delete(lancamento);
    }
}
