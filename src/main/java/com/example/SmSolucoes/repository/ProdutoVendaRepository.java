package com.example.SmSolucoes.repository;

import com.example.SmSolucoes.model.ProdutoVendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVendaModel, Integer> {

    @Query(value = """
    SELECT
    P.nmProduto,
    PV.qtProdutoVendido,
    PV.vlProdutoVendido
    FROM tbProdutoVenda PV
    JOIN tbProduto P ON PV.idProduto = P.idProduto
    WHERE PV.idVenda = :id
    """, nativeQuery = true)
    List<ConsultaProdutosVenda> findProdutosVenda(@Param("id") int id);
}
