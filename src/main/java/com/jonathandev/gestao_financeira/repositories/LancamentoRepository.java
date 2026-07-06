package com.jonathandev.gestao_financeira.repositories;

import com.jonathandev.gestao_financeira.model.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LancamentoRepository extends JpaRepository<LancamentoModel, UUID> {

    @Query("SELECT l FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria")
    List<LancamentoModel> findByCategoriaNome(@Param("nomeCategoria") String nomeCategoria);

    @Query("SELECT SUM(l.preco) FROM LancamentoModel l WHERE l.categoria.categoria = :nomeCategoria")
    BigDecimal calcularTotalPorCategoria(@Param("nomeCategoria") String nomeCategoria);
}
