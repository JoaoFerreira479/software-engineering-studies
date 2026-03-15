package com.food.food.controllers;

import com.food.food.models.Cliente;
import com.food.food.models.FilaDeEspera;
import com.food.food.models.Requisicao;
import com.food.food.services.ClienteService;
import com.food.food.services.FilaDeEsperaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alocarMesa")
public class FilaDeEsperaController {

   @Autowired
    private FilaDeEspera filaDeEspera;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FilaDeEsperaService filaDeEsperaService;

    @GetMapping
    public ResponseEntity<List<Requisicao>> getAll() {
        return ResponseEntity.ok(filaDeEspera.getRequisicoes());
    }

    @PostMapping
    public ResponseEntity<Requisicao> addToWaitList(@RequestBody Requisicao requisicao) {
        Cliente cliente = clienteService.buscaClientePelaid(requisicao.getCliente().getId());
        requisicao.setCliente(cliente);
        filaDeEsperaService.addClienteFila(filaDeEspera, requisicao);
        return ResponseEntity.ok(requisicao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromWaitList(@PathVariable Long id) {
        filaDeEspera.removeClienteFila(id);
        return ResponseEntity.ok().build();
    }
}
