package com.example.SmSolucoes.controller;

import com.example.SmSolucoes.model.Cliente;
import com.example.SmSolucoes.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return clienteService.create(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
       return clienteService.update(id, cliente);
    }
}
