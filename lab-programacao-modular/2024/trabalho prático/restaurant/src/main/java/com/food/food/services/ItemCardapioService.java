package com.food.food.services;

import com.food.food.models.ItemCardapio;
import com.food.food.repositories.ItemCardapioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCardapioService {

    @Autowired
    private ItemCardapioRepository itemCardapioRepository;

    public List<ItemCardapio> getAllItems() {
        return itemCardapioRepository.findAll();
    }

    public ItemCardapio getItemById(Long id) {
        Optional<ItemCardapio> itemCardapio = itemCardapioRepository.findById(id);
        return itemCardapio.orElse(null);
    }

    public ItemCardapio createItem(ItemCardapio itemCardapio) {
        return itemCardapioRepository.save(itemCardapio);
    }

    public ItemCardapio updateItem(Long id, ItemCardapio itemCardapio) {
        Optional<ItemCardapio> existingItemCardapio = itemCardapioRepository.findById(id);
        if (existingItemCardapio.isPresent()) {
            ItemCardapio updatedItemCardapio = existingItemCardapio.get();
            updatedItemCardapio.setNome(itemCardapio.getNome());
            updatedItemCardapio.setPreco(itemCardapio.getPreco());
            updatedItemCardapio.setCategoria(itemCardapio.getCategoria());
            return itemCardapioRepository.save(updatedItemCardapio);
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemCardapioRepository.deleteById(id);
    }
}
