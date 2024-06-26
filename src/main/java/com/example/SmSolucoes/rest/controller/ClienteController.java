package com.example.SmSolucoes.rest.controller;

import com.example.SmSolucoes.rest.dto.ClienteDto;
import com.example.SmSolucoes.rest.form.ClienteForm;
import com.example.SmSolucoes.service.ClienteService;
import com.example.SmSolucoes.service.exceptions.ConstraintException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")

//Anotação para garantir que os endpoints neste controller sempre terá o token de acesso adicionado na requisição,
// se a requisição for feita pelo Swagger
@SecurityRequirement(name = "Keycloack")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<ClienteDto> clienteDtoList = clienteService.ObterTodos();
        return ResponseEntity.ok().body(clienteDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> find(@PathVariable("id") int id) {
        ClienteDto clienteDto = clienteService.ObterPorId(id);
        return ResponseEntity.ok().body(clienteDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteForm clienteForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        clienteService.SalvarCliente(clienteForm);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@Valid @RequestBody ClienteForm clienteForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ClienteDto clienteDto = clienteService.AtualizarCliente(id, clienteForm);
        return ResponseEntity.ok().body(clienteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        clienteService.RemoverCliente(id);
        return ResponseEntity.noContent().build();
    }
}
