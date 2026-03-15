package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.AgenteService;
import com.universidade.aluguelcarros.domain.Agente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agentes")
@Tag(name = "Agentes", description = "Cadastro de agentes")
public class AgenteController {

    private final AgenteService service;

    public AgenteController(AgenteService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo agente")
    public ResponseEntity<Agente> criar(@Valid @RequestBody Agente agente) {
        Agente salvo = service.criar(agente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agente por ID")
    public ResponseEntity<Agente> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os agentes")
    public List<Agente> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar agente")
    public ResponseEntity<Agente> atualizar(@PathVariable Long id, @Valid @RequestBody Agente agente) {
        Agente atualizado = service.atualizar(id, agente);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover agente")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
