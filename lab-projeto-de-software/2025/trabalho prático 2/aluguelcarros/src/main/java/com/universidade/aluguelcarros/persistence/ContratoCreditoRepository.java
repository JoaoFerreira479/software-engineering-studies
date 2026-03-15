package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.ContratoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoCreditoRepository extends JpaRepository<ContratoCredito, Long> {
}
