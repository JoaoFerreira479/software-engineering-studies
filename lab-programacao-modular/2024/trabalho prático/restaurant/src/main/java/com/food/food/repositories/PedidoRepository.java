package com.food.food.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.food.models.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
