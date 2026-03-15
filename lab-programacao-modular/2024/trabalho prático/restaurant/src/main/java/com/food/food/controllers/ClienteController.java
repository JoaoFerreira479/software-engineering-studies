package com.food.food.controllers;

import com.food.food.models.Cliente;
import com.food.food.services.ClienteService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService s) {
        this.clienteService = s;
    }

    @GetMapping("/clientes")
    public @ResponseBody List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/clientes")
    public @ResponseBody Cliente salvaCliente(@RequestBody Cliente cliente) {
        return clienteService.salvaCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public @ResponseBody Cliente getAlunoByMatricula(@PathVariable Long id) {
        return clienteService.buscaClientePelaid(id);
    }

    @PutMapping("/clientes/{id}")
    public @ResponseBody Cliente atualizaDadosCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizaDadosCliente(id, cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public @ResponseBody Cliente deletaCliente(@PathVariable Long id) {
        return clienteService.deletaCliente(id);
    }
}
