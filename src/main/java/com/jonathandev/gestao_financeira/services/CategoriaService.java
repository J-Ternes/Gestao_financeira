package com.jonathandev.gestao_financeira.services;

import com.jonathandev.gestao_financeira.dtos.CategoriaRequestDto;
import com.jonathandev.gestao_financeira.dtos.CategoriaResponseDto;
import com.jonathandev.gestao_financeira.exceptions.CategoriaFoundException;
import com.jonathandev.gestao_financeira.exceptions.CategoriaNotFoundException;
import com.jonathandev.gestao_financeira.exceptions.UserNotFoundException;
import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.UserModel;
import com.jonathandev.gestao_financeira.repositories.CategoriaRepository;
import com.jonathandev.gestao_financeira.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UserRepository userRepository;

    public CategoriaModel criarCategoria(CategoriaRequestDto categoriaDto){
        CategoriaModel verificandoCategoria = categoriaRepository.findByCategoria(categoriaDto.categoria());

        if(verificandoCategoria != null) throw new CategoriaFoundException();


        UserModel usuario = userRepository
                .findById(categoriaDto.usuarioId())
                .orElseThrow(()->new UserNotFoundException());

        CategoriaModel novaCategoria = new CategoriaModel();
        novaCategoria.setCategoria(categoriaDto.categoria());
        novaCategoria.setUsuario(usuario);

        return categoriaRepository.save(novaCategoria);
    }

    public List<CategoriaResponseDto> buscarTodasCategorias(){
        List<CategoriaModel> categoriasCadastradas = categoriaRepository.findAll();

        if (categoriasCadastradas == null) throw new CategoriaNotFoundException();

        List<CategoriaResponseDto> responseDto = categoriasCadastradas.stream()
                .map(categorias-> new CategoriaResponseDto(categorias.getCategoria()))
                .toList();
        return responseDto;
    }

    public void deletar(UUID id){
        CategoriaModel categoria = categoriaRepository.findById(id).orElseThrow(()-> new CategoriaNotFoundException());

        categoriaRepository.delete(categoria);
    }
}
