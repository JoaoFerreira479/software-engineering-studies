package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}
