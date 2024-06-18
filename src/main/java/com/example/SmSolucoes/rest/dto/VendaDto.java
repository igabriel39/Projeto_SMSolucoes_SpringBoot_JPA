package com.example.SmSolucoes.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class VendaDto {

    private int idVenda;
    private String cliente;
    private String dataVenda;
    private float valorVenda;
    private List<ProdutoVendaDto> produtos;
}
