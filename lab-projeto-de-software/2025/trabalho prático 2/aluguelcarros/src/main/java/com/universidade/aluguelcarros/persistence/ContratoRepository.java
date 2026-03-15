package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
}
