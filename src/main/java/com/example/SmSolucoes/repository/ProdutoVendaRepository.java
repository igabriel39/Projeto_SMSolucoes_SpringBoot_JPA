package com.example.SmSolucoes.repository;

import com.example.SmSolucoes.model.ProdutoVendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVendaModel, Integer> {
}
