package com.food.food.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Requisicao {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int numeroDePessoas;

    @Column
    private Date horaDeEntrada;

    @Column
    private Date horaDeSaida;

    @Column
    private boolean isDelivery;

    @Column
    private double distanciaEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

     @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Requisicao() {
    }

    public Requisicao(int numeroDePessoas, Cliente cliente) {
        this.numeroDePessoas = numeroDePessoas;
        this.cliente = cliente;
        this.horaDeEntrada = new Date();
        this.horaDeSaida = null;
    }

    public int getNumeroDePessoas() {
        return numeroDePessoas;
    }

    public void setNumeroDePessoas(int numeroDePessoas) {
        if (getIsDelivery()) {
            this.numeroDePessoas = 0;
        } else {
            this.numeroDePessoas = numeroDePessoas;
        }
    }

    public Date getHoraDeEntrada() {
        return horaDeEntrada;
    }

    public void setHoraDeEntrada(Date horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public Date getHoraDeSaida() {
        return horaDeSaida;
    }

    public void setHoraDeSaida(Date horaDeSaida) {
        this.horaDeSaida = horaDeSaida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        this.cliente = cliente;
    }

    public void definirHoraDeSaida() {
        this.horaDeSaida = new Date();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsDelivery() {
        return this.isDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public double getDistanciaEntrega() {
        return distanciaEntrega;
    }

    public void setDistanciaEntrega(double distanciaEntrega) {
        this.distanciaEntrega = distanciaEntrega;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

}
