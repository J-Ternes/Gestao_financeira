package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaModel criarCategoria(CategoriaRequestDto categoriaDto){
        categoriaRepository.findByCategoria(categoriaDto.categoria())
                .ifPresent(c ->{throw new CategoriaFoundException();});

        CategoriaModel novaCategoria = new CategoriaModel();
        novaCategoria.setCategoria(categoriaDto.categoria());
        novaCategoria.setUsuario(categoriaDto.usuario());

        return categoriaRepository.save(novaCategoria);
    }

}
