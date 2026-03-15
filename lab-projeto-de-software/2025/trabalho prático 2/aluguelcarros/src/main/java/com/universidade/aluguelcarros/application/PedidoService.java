package com.universidade.aluguelcarros.application;

import com.universidade.aluguelcarros.application.exception.RecursoNaoEncontradoException;
import com.universidade.aluguelcarros.domain.*;
import com.universidade.aluguelcarros.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final AgenteRepository agenteRepository;
    private final AutomovelRepository automovelRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         AgenteRepository agenteRepository,
                         AutomovelRepository automovelRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.agenteRepository = agenteRepository;
        this.automovelRepository = automovelRepository;
    }

    @Transactional
    public Pedido criar(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", request.clienteId()));
        Agente agente = agenteRepository.findById(request.agenteId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Agente", request.agenteId()));
        Automovel automovel = automovelRepository.findById(request.automovelId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Automóvel", request.automovelId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setAgente(agente);
        pedido.setAutomovel(automovel);
        pedido.setSituacao(request.situacao() != null ? request.situacao() : "Novo");
        pedido.setObservacoesCliente(request.observacoesCliente());
        pedido.setObservacoesAgente(request.observacoesAgente());

        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido atualizar(Long id, PedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido", id));

        Cliente cliente = clienteRepository.findById(request.clienteId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", request.clienteId()));
        pedido.setCliente(cliente);
        Agente agente = agenteRepository.findById(request.agenteId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Agente", request.agenteId()));
        pedido.setAgente(agente);
        Automovel automovel = automovelRepository.findById(request.automovelId())
            .orElseThrow(() -> new RecursoNaoEncontradoException("Automóvel", request.automovelId()));
        pedido.setAutomovel(automovel);

        if (request.situacao() != null) {
            pedido.setSituacao(request.situacao());
        }
        pedido.setObservacoesCliente(request.observacoesCliente());
        pedido.setObservacoesAgente(request.observacoesAgente());

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public boolean deletarPorId(Long id) {
        if (!pedidoRepository.existsById(id)) {
            return false;
        }
        pedidoRepository.deleteById(id);
        return true;
    }
}
