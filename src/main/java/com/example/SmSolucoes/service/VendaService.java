package com.example.SmSolucoes.service;

import com.example.SmSolucoes.model.VendaModel;
import com.example.SmSolucoes.repository.ConsultaProdutosVenda;
import com.example.SmSolucoes.repository.ConsultaVenda;
import com.example.SmSolucoes.repository.ProdutoVendaRepository;
import com.example.SmSolucoes.repository.VendaRepository;
import com.example.SmSolucoes.rest.dto.ProdutoVendaDto;
import com.example.SmSolucoes.rest.dto.VendaDto;
import com.example.SmSolucoes.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    ProdutoVendaRepository produtoVendaRepository;
    @Autowired
    ModelMapper modelMapper;

    public VendaDto ObterPorId(Integer id) {
        Optional<ConsultaVenda> consultaVenda = vendaRepository.findVendasPorId(id);
        if (!consultaVenda.isPresent())
            throw new ObjectNotFoundException("Venda não encontrada! Código : " + id + ", Tipo: " + VendaModel.class.getName());

        VendaDto vendaDto = modelMapper.map(consultaVenda.get(), VendaDto.class);
        List<ConsultaProdutosVenda> consultaProdutosVendaList = produtoVendaRepository.findProdutosVenda(vendaDto.getIdVenda());

        //Iteração para adicionar no JSON todos os produtos referentes a venda
        vendaDto.setProdutos(consultaProdutosVendaList.stream()
                .map(produtosVenda -> modelMapper.map(produtosVenda, ProdutoVendaDto.class))
                .collect(Collectors.toList()));

        return vendaDto;
    }
}
