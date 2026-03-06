package com.universidade.moedaestudantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidade.moedaestudantil.exception.EntidadeNaoEncontradaException;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.Aluno;
import com.universidade.moedaestudantil.repository.AlunoRepository;

@Service
@Transactional
public class AlunoService {

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo) {
        this.repo = repo;
    }

    public List<Aluno> findAll() { 
        return repo.findAll(); 
    }
    
    public Optional<Aluno> findById(Long id) { 
        return repo.findById(id); 
    }
    
    public Optional<Aluno> findByEmail(String email) {
        return repo.findByEmail(email);
    }
    
    public Optional<Aluno> findByCpf(String cpf) {
        return repo.findByCpf(cpf);
    }

    public Aluno save(Aluno aluno) {
        if (aluno.getId() == null) {
            if (repo.existsByEmail(aluno.getEmail())) {
                throw new RegraNegocioException("Email já cadastrado no sistema");
            }
            if (repo.existsByCpf(aluno.getCpf())) {
                throw new RegraNegocioException("CPF já cadastrado no sistema");
            }
        } else {
            repo.findByEmail(aluno.getEmail())
                    .filter(a -> !a.getId().equals(aluno.getId()))
                    .ifPresent(a -> { throw new RegraNegocioException("Email já cadastrado para outro aluno"); });
            repo.findByCpf(aluno.getCpf())
                    .filter(a -> !a.getId().equals(aluno.getId()))
                    .ifPresent(a -> { throw new RegraNegocioException("CPF já cadastrado para outro aluno"); });
        }

        if (aluno.getSaldo() == null) {
            aluno.setSaldo(0.0);
        }
        return repo.save(aluno);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Aluno", id);
        }
        repo.deleteById(id);
    }
}