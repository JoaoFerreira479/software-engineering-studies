package com.food.food.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FilaDeEspera {

    private List<Requisicao> requisicoes;

    public FilaDeEspera() {
        this.requisicoes = new ArrayList<>();
    }

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(List<Requisicao> requisicoes) {
        this.requisicoes = requisicoes;
    }

    public void addClienteFila(Requisicao requisicao) {
        if (requisicao == null) {
            throw new IllegalArgumentException("Requisição não pode ser nula");
        }
        this.requisicoes.add(requisicao);
    }

    public void removeClienteFila(Long requisicaoId) {
        if (requisicaoId == null) {
            throw new IllegalArgumentException("ID da requisição não pode ser nulo");
        }
        this.requisicoes.removeIf(requisicao -> requisicao.getId().equals(requisicaoId));
    }

    public Requisicao getNextCliente(int capacidade) {
        for (Requisicao requisicao : requisicoes) {
            if (requisicao.getNumeroDePessoas() <= capacidade) {
                return requisicao;
            }
        }
        return null;
    }
}
