package com.food.food.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.food.food.models.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
