package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.ContratoService;
import com.universidade.aluguelcarros.domain.Contrato;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
@Tag(name = "Contratos", description = "Cadastro de contratos de aluguel")
public class ContratoController {

    private final ContratoService service;

    public ContratoController(ContratoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo contrato")
    public ResponseEntity<Contrato> criar(@Valid @RequestBody Contrato contrato) {
        Contrato salvo = service.criar(contrato);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contrato por ID")
    public ResponseEntity<Contrato> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os contratos")
    public List<Contrato> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contrato")
    public ResponseEntity<Contrato> atualizar(@PathVariable Long id, @Valid @RequestBody Contrato contrato) {
        Contrato atualizado = service.atualizar(id, contrato);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover contrato")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
