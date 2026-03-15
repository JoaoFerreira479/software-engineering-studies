package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Emprego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpregoRepository extends JpaRepository<Emprego, Long> {
}
