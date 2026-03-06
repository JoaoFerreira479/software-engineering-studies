package com.universidade.moedaestudantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidade.moedaestudantil.exception.EntidadeNaoEncontradaException;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.Professor;
import com.universidade.moedaestudantil.repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorService {

    private final ProfessorRepository repo;

    public ProfessorService(ProfessorRepository repo) {
        this.repo = repo;
    }

    public List<Professor> findAll() {
        return repo.findAll();
    }

    public Optional<Professor> findById(Long id) {
        return repo.findById(id);
    }

    public Professor save(Professor p) {
        if (p.getId() == null) {
            if (repo.existsByEmail(p.getEmail())) {
                throw new RegraNegocioException("Email já cadastrado no sistema");
            }
            if (repo.existsByCpf(p.getCpf())) {
                throw new RegraNegocioException("CPF já cadastrado no sistema");
            }
        } else {
            repo.findByEmail(p.getEmail())
                    .filter(prof -> !prof.getId().equals(p.getId()))
                    .ifPresent(prof -> { throw new RegraNegocioException("Email já cadastrado para outro professor"); });
            repo.findByCpf(p.getCpf())
                    .filter(prof -> !prof.getId().equals(p.getId()))
                    .ifPresent(prof -> { throw new RegraNegocioException("CPF já cadastrado para outro professor"); });
        }
        if (p.getSaldo() == null) {
            p.setSaldo(0.0);
        }
        return repo.save(p);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Professor", id);
        }
        repo.deleteById(id);
    }
}