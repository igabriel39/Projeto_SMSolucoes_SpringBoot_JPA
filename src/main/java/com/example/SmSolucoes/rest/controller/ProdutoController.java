package com.example.SmSolucoes.rest.controller;

import com.example.SmSolucoes.rest.dto.ProdutoDto;
import com.example.SmSolucoes.rest.form.ProdutoForm;
import com.example.SmSolucoes.service.ProdutoService;
import com.example.SmSolucoes.service.exceptions.ConstraintException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> findAll() {
        List<ProdutoDto> produtoDtoList = produtoService.ObterTodos();
        return ResponseEntity.ok().body(produtoDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> find(@PathVariable("id") int id) {
        ProdutoDto produtoDto = produtoService.ObterPorId(id);
        return ResponseEntity.ok().body(produtoDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoForm produtoForm, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        produtoService.SalvarProduto(produtoForm);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@Valid @RequestBody ProdutoForm produtoForm
            , @PathVariable("id") int id, BindingResult br) {
        if (br.hasErrors())
            throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());

        ProdutoDto produtoDto = produtoService.AtualizarProduto(id, produtoForm);
        return ResponseEntity.ok().body(produtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        produtoService.RemoverProduto(id);
        return ResponseEntity.noContent().build();
    }
}
