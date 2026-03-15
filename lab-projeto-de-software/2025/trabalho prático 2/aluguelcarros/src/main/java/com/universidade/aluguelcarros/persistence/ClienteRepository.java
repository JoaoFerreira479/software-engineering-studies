package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
