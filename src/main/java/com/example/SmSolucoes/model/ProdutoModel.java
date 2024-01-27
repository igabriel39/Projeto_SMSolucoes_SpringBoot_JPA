package com.example.SmSolucoes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbProduto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(name = "nmProduto", length = 50, nullable = false)
    private String nmProduto;

    @Column(name = "vlProduto", nullable = false)
    private float vlProduto;

    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "dataAlteracao")
    private LocalDateTime dataAlteracao;

    //Anotação para antes de persistir um novo registro no banco, preencher a data de cadastro.
    @PrePersist
    public void prePersist()
    {
        this.setDataCadastro(LocalDateTime.now());
    }

    //Anotação para antes de atualizar um registro no banco, preencher a data de atualização.
    @PreUpdate
    public void preUpdate()
    {
        this.setDataAlteracao(LocalDateTime.now());
    }
}

