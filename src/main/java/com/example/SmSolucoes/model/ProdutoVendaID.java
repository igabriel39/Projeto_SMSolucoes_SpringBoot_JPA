package com.example.SmSolucoes.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

//Classe criada para incorporar a Chave Primária Composta do ProdutoVendaModel
@Embeddable
public class ProdutoVendaID implements Serializable {

    private int idVenda;
    private int idProduto;
}
