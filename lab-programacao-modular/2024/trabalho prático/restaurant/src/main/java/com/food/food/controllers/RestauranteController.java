package com.food.food.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.food.models.Pagamento;
import com.food.food.services.RestauranteService;

@RestController
@RequestMapping("/vendas")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Pagamento>> getAllVendas() {
        return ResponseEntity.ok(restauranteService.getAllVendas());
    }

    @GetMapping("/dia")
    public ResponseEntity<List<Pagamento>> getDailySales(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Pagamento> pagamentos = restauranteService.getDailySales(date);
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/recebimentosfuturos")
    public ResponseEntity<List<Pagamento>> getFutureReceivables() {
        List<Pagamento> pagamentos = restauranteService.getFutureReceivables();
        return ResponseEntity.ok(pagamentos);
    }
}
