package com.food.food.services;

import com.food.food.models.FilaDeEspera;
import com.food.food.models.Requisicao;

import org.springframework.stereotype.Service;

@Service
public class FilaDeEsperaService {

    public void addClienteFila(FilaDeEspera filaDeEspera, Requisicao requisicao) {
        filaDeEspera.getRequisicoes().add(requisicao);
    }

    public void removeClienteFila(FilaDeEspera filaDeEspera, Long id) {
        Requisicao requisicaoToRemove = null;
        for (Requisicao requisicao : filaDeEspera.getRequisicoes()) {
            if (requisicao.getId().equals(id)) {
                requisicaoToRemove = requisicao;
                break;
            }
        }
        if (requisicaoToRemove != null) {
            filaDeEspera.getRequisicoes().remove(requisicaoToRemove);
        }
    }
}