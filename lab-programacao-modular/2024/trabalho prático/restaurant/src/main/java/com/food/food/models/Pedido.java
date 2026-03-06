package com.food.food.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Pedido {

    private static final double TAXA_ENTREGA_POR_KM = 1.0;
    private static final double TAXA_COMIDA_LOCAL = 1.10;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private double valorTotalItens;

    @Column
    private LocalDateTime horaDeEntrada;

    @Column
    private double custoPorPessoa;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemCardapio> itens;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "requisicao_id")
    @JsonIgnore
    private Requisicao requisicao;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.horaDeEntrada = LocalDateTime.now();
    }

    @JsonProperty("requisicaoId")
    public Long getClienteId() {
        return requisicao != null ? requisicao.getId() : null;
    }

    public List<ItemCardapio> getItens() {
        return itens;
    }

    public void addItem(ItemCardapio item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        this.itens.add(item);
    }

    public double getValorTotalComTaxas() {
        if (requisicao == null) {
            return getValorTotalItens();
        }
        if (requisicao.getIsDelivery()) {
            double taxaEntrega = TAXA_ENTREGA_POR_KM * requisicao.getDistanciaEntrega();
            return taxaEntrega + getValorTotalItens();
        }
        return getValorTotalItens() * TAXA_COMIDA_LOCAL;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorTotalItens() {
        valorTotalItens = 0;
        for (ItemCardapio item : itens) {
            valorTotalItens += item.getPreco();
        }
        return valorTotalItens;
    }

    public void setItens(List<ItemCardapio> itens) {
        if (itens == null) {
            throw new IllegalArgumentException("Lista de itens não pode ser nula");
        }
        this.itens = itens;
    }

    public Pagamento getPagamento() {
        return this.pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDateTime getHoraDeEntrada() {
        return this.horaDeEntrada;
    }

    public void setHoraDeEntrada(LocalDateTime horaDeEntrada) {
        this.horaDeEntrada = horaDeEntrada;
    }

    public Requisicao getRequisicao() {
        return this.requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public void setValorTotalItens(double valorTotalItens) {
        this.valorTotalItens = valorTotalItens;
    }

    public double getCustoPorPessoa() {
        if (requisicao == null) {
            return 0;
        }
        int numeroDePessoas = requisicao.getNumeroDePessoas();
        if (numeroDePessoas <= 0) {
            return 0;
        }
        custoPorPessoa = getValorTotalComTaxas() / numeroDePessoas;
        return custoPorPessoa;
    }

    public void setCustoPorPessoa(double custoPorPessoa) {
        this.custoPorPessoa = custoPorPessoa;
    }

}
