package com.food.food.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Dinheiro extends Pagamento {
    public Dinheiro() {   
        super("dinheiro");
    }

    @Override
    public void calcularDesconto(double valorTotalItens) {
        setDesconto(0);
        setValorFinal(valorTotalItens);
        setDataRecebimento(LocalDate.now());
    }
}
