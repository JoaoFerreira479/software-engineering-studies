package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.RendimentoService;
import com.universidade.aluguelcarros.domain.Rendimento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendimentos")
@Tag(name = "Rendimentos", description = "Cadastro de rendimentos")
public class RendimentoController {

    private final RendimentoService service;

    public RendimentoController(RendimentoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo rendimento")
    public ResponseEntity<Rendimento> criar(@Valid @RequestBody Rendimento rendimento) {
        Rendimento salvo = service.criar(rendimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar rendimento por ID")
    public ResponseEntity<Rendimento> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os rendimentos")
    public List<Rendimento> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar rendimento")
    public ResponseEntity<Rendimento> atualizar(@PathVariable Long id, @Valid @RequestBody Rendimento rendimento) {
        Rendimento atualizado = service.atualizar(id, rendimento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover rendimento")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
