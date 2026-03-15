package com.food.food;

import com.food.food.models.ItemCardapio;
import com.food.food.repositories.ItemCardapioRepository;
import com.food.food.services.ItemCardapioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemCardapioTest {

    private ItemCardapio itemCardapio;

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private ItemCardapioService itemCardapioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        itemCardapio = new ItemCardapio();
    }

    @Test
    public void testSetNomeValido() {
        itemCardapio.setNome("Pizza");
        assertEquals("Pizza", itemCardapio.getNome());
    }

    @Test
    public void testSetNomeInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            itemCardapio.setNome("");
        });
        assertEquals("Nome não pode ser vazio ou nulo", exception.getMessage());
    }

    @Test
    public void testSetPrecoValido() {
        itemCardapio.setPreco(29.99);
        assertEquals(29.99, itemCardapio.getPreco());
    }

    @Test
    public void testSetPrecoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            itemCardapio.setPreco(-1);
        });
        assertEquals("Preço não pode ser negativo", exception.getMessage());
    }

    @Test
    public void testSetCategoriaValida() {
        itemCardapio.setCategoria("Prato Principal");
        assertEquals("Prato Principal", itemCardapio.getCategoria());
    }

    @Test
    public void testSetCategoriaInvalida() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            itemCardapio.setCategoria("");
        });
        assertEquals("Categoria não pode ser vazia ou nula", exception.getMessage());
    }

    @Test
    public void testServiceGetAllItems() {
        List<ItemCardapio> items = new ArrayList<>();
        items.add(new ItemCardapio(1L, "Pizza", 29.99, "Prato Principal", null));
        items.add(new ItemCardapio(2L, "Salada", 19.99, "Entrada", null));

        when(itemCardapioRepository.findAll()).thenReturn(items);

        List<ItemCardapio> result = itemCardapioService.getAllItems();
        assertEquals(2, result.size());
        verify(itemCardapioRepository, times(1)).findAll();
    }

    @Test
    public void testServiceGetItemById() {
        ItemCardapio item = new ItemCardapio(1L, "Pizza", 29.99, "Prato Principal", null);
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(item));

        ItemCardapio result = itemCardapioService.getItemById(1L);
        assertNotNull(result);
        assertEquals("Pizza", result.getNome());
        verify(itemCardapioRepository, times(1)).findById(1L);
    }

    @Test
    public void testServiceCreateItem() {
        ItemCardapio item = new ItemCardapio(1L, "Pizza", 29.99, "Prato Principal", null);
        when(itemCardapioRepository.save(item)).thenReturn(item);

        ItemCardapio result = itemCardapioService.createItem(item);
        assertNotNull(result);
        assertEquals("Pizza", result.getNome());
        verify(itemCardapioRepository, times(1)).save(item);
    }

    @Test
    public void testServiceUpdateItem() {
        ItemCardapio existingItem = new ItemCardapio(1L, "Pizza", 29.99, "Prato Principal", null);
        ItemCardapio updatedItem = new ItemCardapio(1L, "Hamburger", 19.99, "Prato Principal", null);
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(itemCardapioRepository.save(existingItem)).thenReturn(updatedItem);

        ItemCardapio result = itemCardapioService.updateItem(1L, updatedItem);
        assertNotNull(result);
        assertEquals("Hamburger", result.getNome());
        verify(itemCardapioRepository, times(1)).findById(1L);
        verify(itemCardapioRepository, times(1)).save(existingItem);
    }

    @Test
    public void testServiceDeleteItem() {
        doNothing().when(itemCardapioRepository).deleteById(1L);
        itemCardapioService.deleteItem(1L);
        verify(itemCardapioRepository, times(1)).deleteById(1L);
    }
}
