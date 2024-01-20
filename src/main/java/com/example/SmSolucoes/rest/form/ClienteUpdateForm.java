package com.example.SmSolucoes.rest.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteUpdateForm {

    @NotEmpty
    @NotBlank(message = "O Nome do Cliente não pode estar em branco.")
    @Size(max = 50)
    private String nmCliente;
}
