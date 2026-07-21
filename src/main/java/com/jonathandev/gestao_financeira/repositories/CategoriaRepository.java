package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, UUID> {

     CategoriaModel findByCategoria(String categoria);

     Page<CategoriaModel> findByUsuarioId(UUID usuarioId, Pageable pageable);
}
