package com.universidade.moedaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universidade.moedaestudantil.model.InstituicaoEnsino;

public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, Long> {

    Optional<InstituicaoEnsino> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
}