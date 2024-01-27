package com.example.SmSolucoes.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbProdutoVenda")
public class ProdutoVendaModel {

    @Id
    private int idVenda;

    @Id
    private int idProduto;

    @Column(name = "vlProdutoVendido", nullable = false)
    private float vlProdutoVendido;

    @Column(name = "qtProdutoVendido", nullable = false)
    private int qtProdutoVendido;
}
