package com.food.food.controllers;

import com.food.food.models.FilaDeEspera;
import com.food.food.models.Requisicao;
import com.food.food.services.RequisicaoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private FilaDeEspera filaDeEspera;

    @PostMapping
    public ResponseEntity<Void> processRequisicao(@RequestBody Requisicao requisicao) {
        requisicaoService.processarRequisicao(requisicao, filaDeEspera);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<Requisicao>> getAllRequisicoes() {
        List<Requisicao> requisicoes = requisicaoService.getAllRequisicoes();
        return ResponseEntity.ok(requisicoes);
    }
}