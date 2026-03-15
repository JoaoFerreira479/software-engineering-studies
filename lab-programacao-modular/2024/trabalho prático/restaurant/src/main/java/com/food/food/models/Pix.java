package com.food.food.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Pix extends Pagamento {

    private static final double TAXA_DESCONTO = 0.0145;
    private static final double DESCONTO_MAXIMO = 10.0;

    @Column
    private String nomeEmissorPix;

    public Pix() {
        super("pix");
    }

    @Override
    public void calcularDesconto(double valorTotalItens) {
        double desconto = Math.min(TAXA_DESCONTO * valorTotalItens, DESCONTO_MAXIMO);
        setDesconto(desconto);
        setValorFinal(valorTotalItens - desconto);
        setDataRecebimento(LocalDate.now());
    }

    public String getNomeEmissorPix() {
        return nomeEmissorPix;
    }

    public void setNomeEmissorPix(String nomeEmissorPix) {
        this.nomeEmissorPix = nomeEmissorPix;
    }
}
