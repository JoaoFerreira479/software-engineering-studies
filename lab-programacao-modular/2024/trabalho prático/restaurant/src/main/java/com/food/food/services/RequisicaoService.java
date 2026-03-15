package com.food.food.services;

import com.food.food.models.Cliente;
import com.food.food.models.FilaDeEspera;
import com.food.food.models.Mesa;
import com.food.food.models.Requisicao;
import com.food.food.repositories.MesaRepository;
import com.food.food.repositories.RequisicaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequisicaoService {

    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private RequisicaoRepository requisicaoRepository;
    @Autowired
    private ClienteService clienteService;

    public void processarRequisicao(Requisicao requisicao, FilaDeEspera filaDeEspera) {
        if (requisicao == null) {
            throw new IllegalArgumentException("Requisição não pode ser nula");
        }
        if (requisicao.getCliente() == null) {
            throw new IllegalArgumentException("Requisição deve ter um cliente associado");
        }
        requisicao.setHoraDeEntrada(new Date());
        Cliente cliente = clienteService.buscaClientePelaid(requisicao.getCliente().getId());
        if (!requisicao.getIsDelivery()) {
            List<Mesa> mesas = mesaRepository.findAll();
            Mesa mesaDisponivel = getMesaDisponivel(mesas, requisicao.getNumeroDePessoas());
            if (mesaDisponivel != null) {
                mesaDisponivel.setCliente(cliente);
                mesaDisponivel.setOcupada(true);
                mesaRepository.save(mesaDisponivel);
            } else {
                filaDeEspera.addClienteFila(requisicao);
            }
        }
        requisicaoRepository.save(requisicao);
    }

    private Mesa getMesaDisponivel(List<Mesa> mesas, int numeroDePessoas) {
        return mesas.stream()
                .filter(mesa -> !mesa.isOcupada() && mesa.getCapacidade() >= numeroDePessoas)
                .findFirst()
                .orElse(null);
    }

    public void handleMesaLiberada(Mesa mesa, FilaDeEspera filaDeEspera) {
        Requisicao nextRequisicao = filaDeEspera.getNextCliente(mesa.getCapacidade());
        if (nextRequisicao != null) {
            mesa.setCliente(nextRequisicao.getCliente());
            mesa.setOcupada(true);
            filaDeEspera.removeClienteFila(nextRequisicao.getId());
        } else {
            mesa.setCliente(null);
            mesa.setOcupada(false);
        }
        mesaRepository.save(mesa);
    }

    public List<Requisicao> getAllRequisicoes() {
        return requisicaoRepository.findAll();
    }
}
