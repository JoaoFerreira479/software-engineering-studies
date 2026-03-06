package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.application.exception.RecursoNaoEncontradoException;
import com.universidade.aluguelcarros.domain.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T extends Entidade> {

    private final JpaRepository<T, Long> repository;
    private final String nomeRecurso;

    protected AbstractCrudService(JpaRepository<T, Long> repository, String nomeRecurso) {
        this.repository = repository;
        this.nomeRecurso = nomeRecurso;
    }

    @Transactional
    public T criar(T entidade) {
        return repository.save(entidade);
    }

    @Transactional(readOnly = true)
    public Optional<T> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public T atualizar(Long id, T entidade) {
        repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException(nomeRecurso, id));
        entidade.setId(id);
        return repository.save(entidade);
    }

    @Transactional
    public boolean deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
