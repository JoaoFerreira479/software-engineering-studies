package com.universidade.aluguelcarros.persistence;

import com.universidade.aluguelcarros.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
