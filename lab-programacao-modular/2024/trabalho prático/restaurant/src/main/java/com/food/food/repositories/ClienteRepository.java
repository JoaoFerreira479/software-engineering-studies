package com.food.food.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.food.food.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
