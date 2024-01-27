package com.example.SmSolucoes.repository;

import com.example.SmSolucoes.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

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
}
