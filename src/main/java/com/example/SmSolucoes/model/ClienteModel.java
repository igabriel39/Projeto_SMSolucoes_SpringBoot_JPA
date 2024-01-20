package com.example.SmSolucoes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tbCliente")
public class ClienteModel {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idCliente;

    @Column(name = "nmCliente", length = 50, nullable = false)
    private String nmCliente;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

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
