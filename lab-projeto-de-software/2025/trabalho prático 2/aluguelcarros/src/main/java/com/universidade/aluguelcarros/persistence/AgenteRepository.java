package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, Long> {
}
