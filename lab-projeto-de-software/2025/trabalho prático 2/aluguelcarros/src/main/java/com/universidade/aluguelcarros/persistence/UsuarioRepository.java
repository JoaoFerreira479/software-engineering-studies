package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
