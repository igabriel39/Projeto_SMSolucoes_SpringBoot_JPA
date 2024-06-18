package com.example.SmSolucoes.repository;

import com.example.SmSolucoes.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Integer> {

    @Query(value = """
    SELECT 
    V.idVenda,
    C.nmCliente as cliente,
    V.dataCadastro as dataVenda,
    sum(PV.vlProdutoVendido * PV.qtProdutoVendido) as valorVenda,
    FROM tbVenda V 
    JOIN tbCliente C on  C.idCliente = V.idCliente
    JOIN tbProdutoVenda PV on V.idVenda = PV.idVenda
    GROUP BY V.idVenda, C.nmCliente, V.dataCadastro
            """, nativeQuery = true)
    List<ConsultaVenda> findVendas();

    @Query(value = """
    SELECT 
    V.idVenda,
    C.nmCliente as cliente,
    V.dataCadastro as dataVenda,
    sum(PV.vlProdutoVendido * PV.qtProdutoVendido) as valorVenda,
    FROM tbVenda V 
    JOIN tbCliente C on  C.idCliente = V.idCliente
    JOIN tbProdutoVenda PV on V.idVenda = PV.idVenda
    WHERE V.idVenda = :id
    GROUP BY V.idVenda, C.nmCliente, V.dataCadastro
            """, nativeQuery = true)
    Optional<ConsultaVenda> findVendasPorId(@Param("id") int id);
}
