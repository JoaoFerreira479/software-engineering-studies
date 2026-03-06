package com.food.food.models;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    private Pedido pedido;

    @Column
    private double valorFinal;

    @Column
    private double desconto;

    @Column
    private LocalDate dataRecebimento;

    @Column
    private String metodo;

    public Pagamento() {
    }

    public Pagamento(String metodo) {
        this.metodo = metodo;
    }

    public void calcularDesconto(double valorTotalItens) {
    }

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    @JsonProperty("pedidoId")
    public Long getPedidoId() {
        return pedido != null ? pedido.getId() : null;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public double getDesconto() {
        return desconto;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setDataRecebimento(LocalDate dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
