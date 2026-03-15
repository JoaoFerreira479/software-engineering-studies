package com.food.food.models;

import jakarta.persistence.*;

@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int capacidade;

    @Column
    private boolean ocupada;

    @OneToOne
    @JoinColumn(name = "cliente_id", unique = true)
    private Cliente cliente;

    public Mesa() {
    }

    public Mesa(int capacidade) {
        this.capacidade = capacidade;
        this.ocupada = false;
    }

    public void ocupar(Cliente cliente) {
        setCliente(cliente);
        this.ocupada = true;
    }

    public boolean getOcupada() {
        return this.ocupada;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.capacidade = capacidade;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.ocupada = cliente != null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
