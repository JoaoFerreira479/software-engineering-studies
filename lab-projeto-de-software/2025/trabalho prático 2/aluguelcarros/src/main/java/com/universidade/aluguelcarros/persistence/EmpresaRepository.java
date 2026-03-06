package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
