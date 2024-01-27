package com.example.SmSolucoes.service;

import com.example.SmSolucoes.model.ClienteModel;
import com.example.SmSolucoes.repository.ClienteRepository;
import com.example.SmSolucoes.rest.dto.ClienteDto;
import com.example.SmSolucoes.rest.form.ClienteForm;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteDto> ObterTodos() {
        try
        {
            List<ClienteModel> clienteModels = clienteRepository.findAll();

            return clienteModels.stream()
                    .map(cliente -> modelMapper.map(cliente, ClienteDto.class))
                    .collect(Collectors.toList());
        }
        catch (BusinessRuleException e)
        {
            throw new BusinessRuleException("Não foi possível consultar os Clientes!");
        }
    }

    public ClienteDto ObterPorId(Integer id) {
        try
        {
            ClienteModel clienteModel = clienteRepository.findById(id).get();
            return modelMapper.map(clienteModel, ClienteDto.class);
        }
        catch (NoSuchElementException e)
        {
            throw new ObjectNotFoundException("Cliente não encontrado! Codigo: " + id + ", Tipo: " + ClienteModel.class.getName());
        }
    }

    public void SalvarCliente(ClienteForm clienteForm) {
        try
        {
            ClienteModel clienteNovo = modelMapper.map(clienteForm, ClienteModel.class);
            clienteNovo = clienteRepository.save(clienteNovo);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s).");
        }
    }

    public ClienteDto AtualizarCliente(Integer id, ClienteForm clienteForm) {
        try
        {
            Optional<ClienteModel> clienteExistente = clienteRepository.findById(id);

            if (clienteExistente.isPresent()) {
                ClienteModel clienteAtualizado = clienteExistente.get();
                clienteAtualizado.setNmCliente(clienteForm.getNmCliente());
                clienteAtualizado.setCpf(clienteForm.getCpf());
                clienteAtualizado = clienteRepository.save(clienteAtualizado);

                return modelMapper.map(clienteAtualizado, ClienteDto.class);
            }
            else
            {
                throw new DataIntegrityException("O Código do Cliente não existe na base de dados!");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Campo(s) obrigatório(s) do Cliente não foi(foram) preenchido(s).");
        }
    }

    public void RemoverCliente(Integer id) {
        try
        {
            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);

            }
            else
            {
                throw new DataIntegrityException("O código do Cliente não existe na base de dados!");
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new DataIntegrityException("Não é possível excluir um Cliente!");
        }
    }
}
