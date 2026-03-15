package com.food.food.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ItemCardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private double preco;

    @Column
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "cardapio_id")
    @JsonIgnore
    private Cardapio cardapio;

    public ItemCardapio() {
    }

    public ItemCardapio(Long id, String nome, double preco, String categoria, Cardapio cardapio) {
        this.id = id;
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo");
        }
        this.nome = nome;
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia ou nula");
        }
        this.categoria = categoria;
        this.cardapio = cardapio;
    }

    @JsonProperty("cardapioId")
    public Long getCardapioId() {
        return cardapio != null ? cardapio.getId() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio ou nulo");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoria não pode ser vazia ou nula");
        }
        this.categoria = categoria;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    @Override
    public String toString() {
        return "Item Cardapio [nome item=" + nome + ", Categoria=" + categoria + ", Preco=" + preco + "]";
    }
}
