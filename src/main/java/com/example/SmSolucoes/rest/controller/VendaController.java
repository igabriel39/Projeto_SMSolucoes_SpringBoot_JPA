package com.example.SmSolucoes.rest.controller;

import com.example.SmSolucoes.repository.ConsultaVenda;
import com.example.SmSolucoes.rest.dto.VendaDto;
import com.example.SmSolucoes.service.VendaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venda")

//Anotação para garantir que os endpoints neste controller sempre terá o token de acesso adicionado na requisição,
// se a requisição for feita pelo Swagger
@SecurityRequirement(name = "Keycloack")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/{id}")
    public ResponseEntity<VendaDto> find(@PathVariable("id") int id) {
        VendaDto vendaDto = vendaService.ObterPorId(id);
        return ResponseEntity.ok().body(vendaDto);
    }

}
