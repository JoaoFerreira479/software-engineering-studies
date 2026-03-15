package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Rendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendimentoRepository extends JpaRepository<Rendimento, Long> {
}
