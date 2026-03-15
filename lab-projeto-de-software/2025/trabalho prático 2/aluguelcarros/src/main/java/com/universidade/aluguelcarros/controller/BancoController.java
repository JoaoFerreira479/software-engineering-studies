package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.BancoService;
import com.universidade.aluguelcarros.domain.Banco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bancos")
@Tag(name = "Bancos", description = "Cadastro de bancos")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar novo banco")
    public ResponseEntity<Banco> criar(@Valid @RequestBody Banco banco) {
        Banco salvo = service.criar(banco);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar banco por ID")
    public ResponseEntity<Banco> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todos os bancos")
    public List<Banco> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar banco")
    public ResponseEntity<Banco> atualizar(@PathVariable Long id, @Valid @RequestBody Banco banco) {
        Banco atualizado = service.atualizar(id, banco);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover banco")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
