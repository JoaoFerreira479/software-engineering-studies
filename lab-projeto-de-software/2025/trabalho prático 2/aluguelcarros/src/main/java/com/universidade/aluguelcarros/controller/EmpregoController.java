package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.EmpregoService;
import com.universidade.aluguelcarros.domain.Emprego;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empregos")
@Tag(name = "Empregos", description = "Cadastro de empregos")
public class EmpregoController {

    private final EmpregoService service;

    public EmpregoController(EmpregoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo emprego")
    public ResponseEntity<Emprego> criar(@Valid @RequestBody Emprego emprego) {
        Emprego salvo = service.criar(emprego);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar emprego por ID")
    public ResponseEntity<Emprego> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os empregos")
    public List<Emprego> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar emprego")
    public ResponseEntity<Emprego> atualizar(@PathVariable Long id, @Valid @RequestBody Emprego emprego) {
        Emprego atualizado = service.atualizar(id, emprego);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover emprego")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
