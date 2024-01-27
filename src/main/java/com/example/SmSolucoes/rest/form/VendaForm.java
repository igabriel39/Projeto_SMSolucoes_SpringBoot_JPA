package com.example.SmSolucoes.rest.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class VendaForm {

    @NotNull
    private int idCliente;

    private List<ProdutoVendaForm> produtos;
}
