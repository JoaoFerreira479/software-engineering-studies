package com.food.food.controllers;

import com.food.food.models.ItemCardapio;
import com.food.food.services.ItemCardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CardapioController {

    @Autowired
    private ItemCardapioService itemCardapioService;

    @GetMapping("/cardapio")
    public ResponseEntity<List<ItemCardapio>> getCardapio() {
        return ResponseEntity.ok(itemCardapioService.getAllItems());
    }

    @GetMapping("/cardapio/itemCardapio/{id}")
    public ResponseEntity<ItemCardapio> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemCardapioService.getItemById(id));
    }

    @PostMapping("/cardapio/itemCardapio")
    public ResponseEntity<ItemCardapio> createItem(@RequestBody ItemCardapio itemCardapio) {
        return ResponseEntity.ok(itemCardapioService.createItem(itemCardapio));
    }

    @PutMapping("/cardapio/itemCardapio/{id}")
    public ResponseEntity<ItemCardapio> updateItem(@PathVariable Long id, @RequestBody ItemCardapio itemCardapio) {
        return ResponseEntity.ok(itemCardapioService.updateItem(id, itemCardapio));
    }

    @DeleteMapping("/cardapio/itemCardapio/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemCardapioService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

}
