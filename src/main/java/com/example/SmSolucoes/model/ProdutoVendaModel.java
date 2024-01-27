package com.example.SmSolucoes.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbProdutoVenda")
public class ProdutoVendaModel {

    //Incorporando Chave Primária Composta
    @EmbeddedId
    private ProdutoVendaID produtoVendaID;

    @Column(name = "vlProdutoVendido", nullable = false)
    private float vlProdutoVendido;

    @Column(name = "qtProdutoVendido", nullable = false)
    private int qtProdutoVendido;
}
