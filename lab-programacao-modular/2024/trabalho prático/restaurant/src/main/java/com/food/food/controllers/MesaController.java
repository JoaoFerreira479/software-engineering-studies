package com.food.food.controllers;

import com.food.food.models.Cliente;
import com.food.food.models.FilaDeEspera;
import com.food.food.models.Mesa;
import com.food.food.services.RequisicaoService;
import com.food.food.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private FilaDeEspera filaDeEspera;

    @GetMapping
    public ResponseEntity<List<Mesa>> getAllMesas() {
        List<Mesa> mesas = restauranteService.getMesas();
        return ResponseEntity.ok(mesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesa(@PathVariable Long id) {
        Mesa mesa = restauranteService.getMesaById(id);
        return mesa != null ? ResponseEntity.ok(mesa) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> assignCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Mesa mesa = restauranteService.getMesaById(id);
        if (mesa != null) {
            mesa.setCliente(cliente);
            mesa.setOcupada(true);
            restauranteService.saveMesa(mesa);
            return ResponseEntity.ok(mesa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/liberarMesa")
    public ResponseEntity<Mesa> liberarMesa(@PathVariable Long id) {
        Mesa mesa = restauranteService.getMesaById(id);
        if (mesa != null) {
            requisicaoService.handleMesaLiberada(mesa, filaDeEspera);
            return ResponseEntity.ok(mesa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
