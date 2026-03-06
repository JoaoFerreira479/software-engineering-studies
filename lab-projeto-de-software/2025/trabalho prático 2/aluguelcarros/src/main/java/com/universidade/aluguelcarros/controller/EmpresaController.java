package com.universidade.aluguelcarros.controller;

import com.universidade.aluguelcarros.application.EmpresaService;
import com.universidade.aluguelcarros.domain.Empresa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@Tag(name = "Empresas", description = "Cadastro de empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Criar nova empresa")
    public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa) {
        Empresa salvo = service.criar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empresa por ID")
    public ResponseEntity<Empresa> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Listar todas as empresas")
    public List<Empresa> listar() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empresa")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
        Empresa atualizada = service.atualizar(id, empresa);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover empresa")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return service.deletarPorId(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
