package com.food.food.services;

import com.food.food.models.Mesa;
import com.food.food.models.Pagamento;
import com.food.food.models.Pedido;
import com.food.food.repositories.MesaRepository;
import com.food.food.repositories.PagamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> getMesas() {
        return mesaRepository.findAll();
    }

    public Mesa getMesaById(Long id) {
        Optional<Mesa> mesa = mesaRepository.findById(id);
        return mesa.orElse(null);
    }

    @Transactional
    public void saveMesa(Mesa mesa) {
        mesaRepository.save(mesa);
    }

    public List<Pagamento> getAllVendas() {
        return pagamentoRepository.findAll();
    }

    public List<Pagamento> getDailySales(LocalDate date) {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        System.out.println("Total Pagamentos: " + pagamentos.size());
        return pagamentos.stream()
                .filter(pagamento -> {
                    Pedido pedido = pagamento.getPedido();
                    boolean isEqual = pedido != null && pedido.getHoraDeEntrada().toLocalDate().isEqual(date);
                    System.out.println("Pagamento ID: " + pagamento.getId() + ", Pedido: "
                            + (pedido != null ? pedido.getId() : "null") + ", isEqual: " + isEqual);
                    return isEqual;
                })
                .collect(Collectors.toList());
    }

    public List<Pagamento> getFutureReceivables() {
        LocalDate today = LocalDate.now().plusDays(1);
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        System.out.println("Total Pagamentos: " + pagamentos.size());
        return pagamentos.stream()
                .filter(pagamento -> pagamento.getDataRecebimento() != null &&
                        (pagamento.getDataRecebimento().isEqual(today) || pagamento.getDataRecebimento().isAfter(today)))
                .collect(Collectors.toList());
    }

}
