package com.example.SmSolucoes.rest.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProdutoVendaForm {

    @NotNull
    private int idProduto;

    @Min(value = 1, message = "Quantidade do produto deve ser igual ou superior a 1.")
    private int qtProdutoVendido;
}
