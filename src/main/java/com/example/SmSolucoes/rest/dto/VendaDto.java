package com.example.SmSolucoes.rest.dto;

import lombok.Data;

@Data
public class VendaDto {

    private int idVenda;
    private String cliente;
    private String dataVenda;
    private float valorVenda;
}
