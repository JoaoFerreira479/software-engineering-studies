package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.AutomovelService;
import com.universidade.aluguelcarros.domain.Automovel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automoveis")
@Tag(name = "Automóveis", description = "Cadastro de automóveis")
public class AutomovelController {

    private final AutomovelService service;

    public AutomovelController(AutomovelService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo automóvel")
    public ResponseEntity<Automovel> criar(@Valid @RequestBody Automovel automovel) {
        Automovel salvo = service.criar(automovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar automóvel por ID")
    public ResponseEntity<Automovel> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os automóveis")
    public List<Automovel> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar automóvel")
    public ResponseEntity<Automovel> atualizar(@PathVariable Long id, @Valid @RequestBody Automovel automovel) {
        Automovel atualizado = service.atualizar(id, automovel);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover automóvel")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
