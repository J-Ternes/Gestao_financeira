package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.dtos.CategoriaResponseDto;
import com.jonathandev.gestao_financeira.dtos.PaginaResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaFoundException;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.exceptions.IncompatibleUserException;
import com.jonathandev.gestao_financeira.helpers.Helpers;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.repositories.CategoriaRepository;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;
    private final Helpers helpers;

    public CategoriaModel criarCategoria(CategoriaRequestDto categoriaDto){

        CategoriaModel verificandoCategoria = categoriaRepository.findByCategoria(categoriaDto.categoria());

        if(verificandoCategoria != null) throw new CategoriaFoundException();


        UserModel usuario = helpers.getUsuarioAutenticado();

        CategoriaModel novaCategoria = new CategoriaModel();
        novaCategoria.setCategoria(categoriaDto.categoria());
        novaCategoria.setUsuario(usuario);

        return categoriaRepository.save(novaCategoria);
    }

    public PaginaResponseDto<CategoriaResponseDto> buscarTodasCategorias(int pagina, int tamanho, String ordenarPor){

        UserModel usuario = helpers.getUsuarioAutenticado();

        Pageable pageable = PageRequest.of(pagina,tamanho, Sort.by(Sort.Direction.DESC,ordenarPor));

        Page<CategoriaModel> page = categoriaRepository.findByUsuarioId(usuario.getId(), pageable);

        List<CategoriaResponseDto> conteudo = page.getContent()
                .stream()
                .map(categoria-> new CategoriaResponseDto(categoria.getCategoria())).toList();

        return new PaginaResponseDto(conteudo,page.getNumber(),page.getSize(),page.getTotalElements(),page.getTotalPages());
    }

    public void deletar(UUID id){

        UserModel usuario = helpers.getUsuarioAutenticado();

        CategoriaModel categoria = categoriaRepository.findById(id).orElseThrow(()-> new CategoriaNotFoundException());

        if (!categoria.getUsuario().getId().equals(usuario.getId())) throw new IncompatibleUserException();

        categoriaRepository.delete(categoria);
    }
}
