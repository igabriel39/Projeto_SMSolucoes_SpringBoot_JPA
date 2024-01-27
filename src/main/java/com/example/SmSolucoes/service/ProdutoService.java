package com.example.SmSolucoes.service;

import com.example.SmSolucoes.model.ClienteModel;
import com.example.SmSolucoes.model.ProdutoModel;
import com.example.SmSolucoes.repository.ClienteRepository;
import com.example.SmSolucoes.repository.ProdutoRepository;
import com.example.SmSolucoes.rest.dto.ClienteDto;
import com.example.SmSolucoes.rest.dto.ProdutoDto;
import com.example.SmSolucoes.rest.form.ClienteForm;
import com.example.SmSolucoes.rest.form.ProdutoForm;
import com.example.SmSolucoes.service.exceptions.BusinessRuleException;
import com.example.SmSolucoes.service.exceptions.DataIntegrityException;
import com.example.SmSolucoes.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProdutoDto> ObterTodos() {
        try
        {
            List<ProdutoModel> produtoModels = produtoRepository.findAll();

            return produtoModels.stream()
                    .map(produto -> modelMapper.map(produto, ProdutoDto.class))
                    .collect(Collectors.toList());
        }
        catch (BusinessRuleException e)
        {
            throw new BusinessRuleException("Não foi possível consultar os Produtos!");
        }
    }

    public ProdutoDto ObterPorId(Integer id) {
        try
        {
            ProdutoModel produtoModel = produtoRepository.findById(id).get();
            return modelMapper.map(produtoModel, ProdutoDto.class);
        }
        catch (NoSuchElementException e)
        {
            throw new ObjectNotFoundException("Produto não encontrado! Codigo: " + id + ", Tipo: " + ProdutoModel.class.getName());
        }
    }

    public void SalvarProduto(ProdutoForm produtoForm) {
        try
        {
            ProdutoModel produtoNovo = modelMapper.map(produtoForm, ProdutoModel.class);
            produtoNovo = produtoRepository.save(produtoNovo);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Produto não foi(foram) preenchido(s).");
        }
    }

    public ProdutoDto AtualizarProduto(Integer id, ProdutoForm produtoForm) {
        try
        {
            Optional<ProdutoModel> produtoExistente = produtoRepository.findById(id);

            if (produtoExistente.isPresent()) {
                ProdutoModel produtoAtualizado = produtoExistente.get();
                produtoAtualizado.setNmProduto(produtoForm.getNmProduto());
                produtoAtualizado.setVlProduto(produtoForm.getVlProduto());
                produtoAtualizado = produtoRepository.save(produtoAtualizado);

                return modelMapper.map(produtoAtualizado, ProdutoDto.class);
            }
            else
            {
                throw new DataIntegrityException("O Código do Produto não existe na base de dados!");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Produto não foi(foram) preenchido(s).");
        }
    }

    public void RemoverProduto(Integer id) {
        try
        {
            if (produtoRepository.existsById(id)) {
                produtoRepository.deleteById(id);

            }
            else
            {
                throw new DataIntegrityException("O código do Produto não existe na base de dados!");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Não é possível excluir um Produto!");
        }
    }
}
