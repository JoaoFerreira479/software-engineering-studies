package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.ContratoCreditoService;
import com.universidade.aluguelcarros.domain.ContratoCredito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos-credito")
@Tag(name = "Contratos de Crédito", description = "Cadastro de contratos de crédito")
public class ContratoCreditoController {

    private final ContratoCreditoService service;

    public ContratoCreditoController(ContratoCreditoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo contrato de crédito")
    public ResponseEntity<ContratoCredito> criar(@Valid @RequestBody ContratoCredito contratoCredito) {
        ContratoCredito salvo = service.criar(contratoCredito);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato de crédito por ID")
    public ResponseEntity<ContratoCredito> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os contratos de crédito")
    public List<ContratoCredito> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contrato de crédito")
    public ResponseEntity<ContratoCredito> atualizar(@PathVariable Long id, @Valid @RequestBody ContratoCredito contratoCredito) {
        ContratoCredito atualizado = service.atualizar(id, contratoCredito);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover contrato de crédito")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
