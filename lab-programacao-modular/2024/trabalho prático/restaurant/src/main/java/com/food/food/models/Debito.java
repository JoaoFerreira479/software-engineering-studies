package com.food.food.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Debito extends Pagamento {

    private static final double TAXA_DESCONTO = 0.014;

    @Column
    private String nomeBancoDebito;

    public Debito() {
        super("debito");
    }

    @Override
    public void calcularDesconto(double valorTotalItens) {
        double desconto = TAXA_DESCONTO * valorTotalItens;
        setDesconto(desconto);
        setValorFinal(valorTotalItens - desconto);
        setDataRecebimento(LocalDate.now().plusDays(14));
    }

    public String getNomeBancoDebito() {
        return nomeBancoDebito;
    }

    public void setNomeBancoDebito(String nomeBancoDebito) {
        this.nomeBancoDebito = nomeBancoDebito;
    }
}
