package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.CategoriaModel;
import com.jonathandev.gestao_financeira.model.LancamentoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, UUID> {

    Page<LancamentoModel> findByUsuarioId(UUID usuarioId, Pageable pageable);

    @Query("SELECT l FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria")
    Page<LancamentoModel> findByCategoriaNomePaginado(@Param("nomeCategoria") String nomeCategoria, Pageable pageable);

    @Query("SELECT SUM(l.preco) FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria")
    BigDecimal calcularTotalPorCategoria(@Param("nomeCategoria") String nomeCategoria);

}
