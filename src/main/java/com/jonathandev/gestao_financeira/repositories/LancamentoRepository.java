package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, UUID> {
    List<CategoriaModel> findByCategoria(String categoria);
}
