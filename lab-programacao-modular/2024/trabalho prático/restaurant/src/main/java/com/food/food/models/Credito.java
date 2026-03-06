package com.food.food.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Credito extends Pagamento {

    private static final double TAXA_DESCONTO = 0.031;

    @Column
    private String bandeiraCartao;

    public Credito() {
        super("credito");
    }

    @Override
    public void calcularDesconto(double valorTotalItens) {
        double desconto = TAXA_DESCONTO * valorTotalItens;
        setDesconto(desconto);
        setValorFinal(valorTotalItens - desconto);
        setDataRecebimento(LocalDate.now().plusDays(30));
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }
}
