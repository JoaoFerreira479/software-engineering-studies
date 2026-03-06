package com.universidade.moedaestudantil.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.universidade.moedaestudantil.dto.ExtratoResponse;
import com.universidade.moedaestudantil.dto.TransacaoItemDTO;
import com.universidade.moedaestudantil.model.Aluno;
import com.universidade.moedaestudantil.model.Transacao;
import com.universidade.moedaestudantil.service.AlunoService;
import com.universidade.moedaestudantil.service.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService service;
    private final TransacaoService transacaoService;

    public AlunoController(AlunoService service, TransacaoService transacaoService) {
        this.service = service;
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> all() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@Valid @RequestBody Aluno aluno) {
        Aluno saved = service.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Long id, @Valid @RequestBody Aluno aluno) {
        return service.findById(id)
                .map(existing -> {
                    aluno.setId(id);
                    return ResponseEntity.ok(service.save(aluno));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<?> getExtrato(@PathVariable Long id) {
        return service.findById(id)
                .map(aluno -> {
                    List<Transacao> transacoes = transacaoService.findByAlunoId(id);
                    List<TransacaoItemDTO> itens = transacoes.stream()
                            .map(TransacaoItemDTO::from)
                            .toList();
                    return ResponseEntity.ok(new ExtratoResponse(aluno.getSaldo(), itens));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}