package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {
}
