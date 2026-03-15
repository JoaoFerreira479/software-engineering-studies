package com.food.food.services;

import com.food.food.models.Cliente;
import com.food.food.repositories.ClienteRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository r) {
        this.clienteRepository = r;
    }

    public Cliente buscaClientePelaid(Long id) {
        return clienteRepository.findById(id)
                .orElse(null);
    }

    public Cliente salvaCliente(Cliente a) {
        return clienteRepository.save(a);
    }

    public Cliente atualizaDadosCliente(Long id, Cliente a) {
        return clienteRepository
                .findById(id)
                .map(clienteBD -> {
                    clienteBD.setTelefone(a.getTelefone());
                    clienteBD.setNome(a.getNome());

                    return salvaCliente(clienteBD);
                })
                .orElse(null);
    }

    public Cliente deletaCliente(Long id) {
        return clienteRepository
                .findById(id)
                .map(clienteBD -> {
                    clienteRepository.delete(clienteBD);
                    return clienteBD;
                })
                .orElse(null);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

}
