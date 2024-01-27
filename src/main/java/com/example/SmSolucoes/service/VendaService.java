package com.example.SmSolucoes.service;

import com.example.SmSolucoes.repository.ConsultaVenda;
import com.example.SmSolucoes.repository.VendaRepository;
import com.example.SmSolucoes.rest.dto.VendaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    VendaRepository vendaRepository;

    public List<ConsultaVenda> ObterTodos()
    {
        List<ConsultaVenda> teste = vendaRepository.findVendas();
        return teste;
    }
}
