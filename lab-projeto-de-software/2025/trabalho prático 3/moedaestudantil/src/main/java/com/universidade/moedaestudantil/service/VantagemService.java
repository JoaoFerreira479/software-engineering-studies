package com.universidade.moedaestudantil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.universidade.moedaestudantil.exception.EntidadeNaoEncontradaException;
import com.universidade.moedaestudantil.exception.RegraNegocioException;
import com.universidade.moedaestudantil.model.EmpresaParceira;
import com.universidade.moedaestudantil.model.Vantagem;
import com.universidade.moedaestudantil.repository.EmpresaParceiraRepository;
import com.universidade.moedaestudantil.repository.VantagemRepository;

@Service
@Transactional
public class VantagemService {

    private final VantagemRepository repo;
    private final EmpresaParceiraRepository empresaRepo;

    public VantagemService(VantagemRepository repo, EmpresaParceiraRepository empresaRepo) { 
        this.repo = repo;
        this.empresaRepo = empresaRepo;
    }

    public List<Vantagem> findAll() { 
        return repo.findAll(); 
    }
    
    public Optional<Vantagem> findById(Long id) { 
        return repo.findById(id); 
    }

    public Vantagem save(Vantagem vantagem) {
        if (vantagem.getEmpresa() == null || vantagem.getEmpresa().getId() == null) {
            throw new RegraNegocioException("Empresa é obrigatória para cadastrar uma vantagem");
        }
        EmpresaParceira empresa = empresaRepo.findById(vantagem.getEmpresa().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Empresa", vantagem.getEmpresa().getId()));
        vantagem.setEmpresa(empresa);

        if (vantagem.getCusto() == null || vantagem.getCusto() <= 0) {
            throw new RegraNegocioException("Custo deve ser um valor positivo");
        }
        if (vantagem.getDescricao() == null || vantagem.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("Descrição é obrigatória");
        }
        return repo.save(vantagem);
    }

    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Vantagem", id);
        }
        repo.deleteById(id);
    }
}