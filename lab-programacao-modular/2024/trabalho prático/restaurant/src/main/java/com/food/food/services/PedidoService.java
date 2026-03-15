package com.food.food.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.food.DTO.PedidoRequest;
import com.food.food.models.ItemCardapio;
import com.food.food.models.Pedido;
import com.food.food.models.Requisicao;
import com.food.food.repositories.ItemCardapioRepository;
import com.food.food.repositories.PedidoRepository;
import com.food.food.repositories.RequisicaoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    public Pedido createPedido(PedidoRequest pedidoRequest) {
        if (pedidoRequest == null) {
            throw new IllegalArgumentException("Requisição de pedido não pode ser nula");
        }
        if (pedidoRequest.getRequisicaoId() == null) {
            throw new IllegalArgumentException("ID da requisição é obrigatório");
        }
        if (pedidoRequest.getItensIds() == null || pedidoRequest.getItensIds().isEmpty()) {
            throw new IllegalArgumentException("Pedido deve ter pelo menos um item");
        }
        Pedido pedido = new Pedido();

        Requisicao requisicao = requisicaoRepository.findById(pedidoRequest.getRequisicaoId())
                .orElseThrow(() -> new RuntimeException("Requisicao não encontrada"));

        List<ItemCardapio> itens = new ArrayList<>();
        for (Long itemId : pedidoRequest.getItensIds()) {
            ItemCardapio item = itemCardapioRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item de cardápio não encontrado"));
            itens.add(item);
        }
        pedido.setItens(itens);

        pedido.setRequisicao(requisicao);

        double valorTotalItens = itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
        pedido.setValorTotalItens(valorTotalItens);

        return pedidoRepository.save(pedido);
    }

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido nao encontrado"));
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido updatePedido(Long id, PedidoRequest pedidoRequest) {
        if (pedidoRequest == null || pedidoRequest.getItensIds() == null || pedidoRequest.getItensIds().isEmpty()) {
            throw new IllegalArgumentException("Requisição de pedido inválida ou sem itens");
        }
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        List<ItemCardapio> itens = new ArrayList<>();
        for (Long itemId : pedidoRequest.getItensIds()) {
            ItemCardapio item = itemCardapioRepository.findById(itemId)
                    .orElseThrow(() -> new RuntimeException("Item de cardápio não encontrado"));
            itens.add(item);
        }
        pedido.setItens(itens);

        double valorTotal = itens.stream().mapToDouble(ItemCardapio::getPreco).sum();
        pedido.setValorTotalItens(valorTotal);

        return pedidoRepository.save(pedido);
    }
}
