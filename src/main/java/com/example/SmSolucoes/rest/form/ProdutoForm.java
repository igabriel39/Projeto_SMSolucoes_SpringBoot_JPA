package com.example.SmSolucoes.rest.form;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProdutoForm {

    @NotEmpty
    @NotBlank(message = "O Nome do Produto não pode estar em branco.")
    @Size(max = 50)
    private String nmProduto;

    @Positive(message = "Valor do Produto não pode ser menor ou igual a zero.")
    private float vlProduto;

}
