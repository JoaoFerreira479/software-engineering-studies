package com.food.food.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.food.food.DTO.PagamentoRequest;
import com.food.food.models.Pagamento;
import com.food.food.services.PagamentoService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private static final Logger LOGGER = Logger.getLogger(PagamentoController.class.getName());

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<Pagamento> processPayment(@PathVariable Long pedidoId, @RequestBody PagamentoRequest pagamentoRequest) {
        if (pagamentoRequest.getMetodo() == null || pagamentoRequest.getMetodo().isEmpty()) {
            LOGGER.severe("Payment method is missing or empty");
            return ResponseEntity.badRequest().body(null);
        }
        try {
            LOGGER.info("Processing payment for Pedido ID: " + pedidoId);
            Pagamento pagamento = pagamentoService.processPayment(pedidoId, pagamentoRequest);
            return ResponseEntity.ok(pagamento);
        } catch (RuntimeException e) {
            LOGGER.severe("Error processing payment: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable Long id) {
        try {
            Pagamento pagamento = pagamentoService.getPagamentoById(id);
            return ResponseEntity.ok(pagamento);
        } catch (RuntimeException e) {
            LOGGER.severe("Error fetching Pagamento by ID: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {
        try {
            List<Pagamento> pagamentos = pagamentoService.getAllPagamentos();
            return ResponseEntity.ok(pagamentos);
        } catch (RuntimeException e) {
            LOGGER.severe("Error fetching all Pagamentos: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }
}
