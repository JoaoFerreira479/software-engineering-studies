package com.universidade.moedaestudantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidade.moedaestudantil.exception.EntidadeNaoEncontradaException;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.EmpresaParceira;
import com.universidade.moedaestudantil.repository.EmpresaParceiraRepository;

@Service
@Transactional
public class EmpresaParceiraService {

    private final EmpresaParceiraRepository repo;

    public EmpresaParceiraService(EmpresaParceiraRepository repo) { 
        this.repo = repo; 
    }

    public List<EmpresaParceira> findAll() { 
        return repo.findAll(); 
    }
    
    public Optional<EmpresaParceira> findById(Long id) { 
        return repo.findById(id); 
    }
    
    public Optional<EmpresaParceira> findByEmail(String email) {
        return repo.findByEmail(email);
    }
    
    public Optional<EmpresaParceira> findByCnpj(String cnpj) {
        return repo.findByCnpj(cnpj);
    }

    public EmpresaParceira save(EmpresaParceira empresa) {
        if (empresa.getId() == null) {
            if (repo.existsByEmail(empresa.getEmail())) {
                throw new RegraNegocioException("Email já cadastrado no sistema");
            }
            if (repo.existsByCnpj(empresa.getCnpj())) {
                throw new RegraNegocioException("CNPJ já cadastrado no sistema");
            }
        } else {
            repo.findByEmail(empresa.getEmail())
                    .filter(e -> !e.getId().equals(empresa.getId()))
                    .ifPresent(e -> { throw new RegraNegocioException("Email já cadastrado para outra empresa"); });
            repo.findByCnpj(empresa.getCnpj())
                    .filter(e -> !e.getId().equals(empresa.getId()))
                    .ifPresent(e -> { throw new RegraNegocioException("CNPJ já cadastrado para outra empresa"); });
        }
        return repo.save(empresa);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Empresa parceira", id);
        }
        repo.deleteById(id);
    }
}