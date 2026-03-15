package com.universidade.moedaestudantil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.universidade.moedaestudantil.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByEmail(String email);

    Optional<Professor> findByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}