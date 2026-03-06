package com.food.food.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.food.DTO.PagamentoRequest;
import com.food.food.models.Credito;
import com.food.food.models.Debito;
import com.food.food.models.Dinheiro;
import com.food.food.models.Pagamento;
import com.food.food.models.Pedido;
import com.food.food.models.Pix;
import com.food.food.repositories.PagamentoRepository;
import com.food.food.repositories.PedidoRepository;

import java.util.List;

import java.util.logging.Logger;

@Service
public class PagamentoService {

    private static final Logger LOGGER = Logger.getLogger(PagamentoService.class.getName());

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pagamento processPayment(Long pedidoId, PagamentoRequest pagamentoRequest) {
        if (pagamentoRequest == null) {
            throw new IllegalArgumentException("Requisição de pagamento não pode ser nula");
        }
        String metodo = pagamentoRequest.getMetodo();
        if (metodo == null || metodo.isBlank()) {
            throw new IllegalArgumentException("Método de pagamento não pode ser nulo ou vazio");
        }
        LOGGER.info(() -> "Fetching Pedido with ID: " + pedidoId);
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));

        Pagamento pagamento = switch (metodo.trim().toLowerCase()) {
            case "credito" -> {
                Credito credito = new Credito();
                credito.setBandeiraCartao(pagamentoRequest.getBandeiraCartao());
                yield credito;
            }
            case "debito" -> {
                Debito debito = new Debito();
                debito.setNomeBancoDebito(pagamentoRequest.getNomeBancoDebito());
                yield debito;
            }
            case "pix" -> {
                Pix pix = new Pix();
                pix.setNomeEmissorPix(pagamentoRequest.getNomeEmissorPix());
                yield pix;
            }
            case "dinheiro" -> new Dinheiro();
            default -> throw new RuntimeException("Método de pagamento não suportado");
        };
    
        double valorTotalItens = pedido.getValorTotalComTaxas();
        pagamento.calcularDesconto(valorTotalItens);
        pagamento.setPedido(pedido);
        pagamento.setMetodo(pagamentoRequest.getMetodo());
    
        pagamento = pagamentoRepository.save(pagamento);
        pedido.setPagamento(pagamento);
    
        pedidoRepository.save(pedido);
    
        return pagamento;
    }    
    

    public Pagamento getPagamentoById(Long id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento not found with ID: " + id));
    }

    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }
}
