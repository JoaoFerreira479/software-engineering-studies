package com.food.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.food.food.models.Restaurante;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante("Restaurante Exemplo", "Endereço Exemplo");
    }

    @Test
    public void testConstructor() {
        assertNotNull(restaurante);
        assertEquals("Restaurante Exemplo", restaurante.getNome());
        assertEquals("Endereço Exemplo", restaurante.getEndereco());
    }

    @Test
    public void testConstructorThrowsExceptionWhenNomeIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> criarRestaurante(null, "Endereço Exemplo"));
        assertEquals("Nome não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testConstructorThrowsExceptionWhenNomeIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> criarRestaurante("", "Endereço Exemplo"));
        assertEquals("Nome não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testConstructorThrowsExceptionWhenEnderecoIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> criarRestaurante("Restaurante Exemplo", null));
        assertEquals("Endereço não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testConstructorThrowsExceptionWhenEnderecoIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> criarRestaurante("Restaurante Exemplo", ""));
        assertEquals("Endereço não pode ser nulo ou vazio", exception.getMessage());
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    private static void criarRestaurante(String nome, String endereco) {
        new Restaurante(nome, endereco);
    }

    @Test
    public void testSetNomeThrowsExceptionWhenNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setNome(null);
        });
        assertEquals("Nome não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testSetNomeThrowsExceptionWhenEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setNome("");
        });
        assertEquals("Nome não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testSetEndereco() {
        restaurante.setEndereco("Novo Endereço");
        assertEquals("Novo Endereço", restaurante.getEndereco());
    }

    @Test
    public void testSetEnderecoToNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setEndereco(null);
        });
        assertEquals("Endereço não pode ser nulo ou vazio", exception.getMessage());
    }

    @Test
    public void testSetEnderecoToEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            restaurante.setEndereco("");
        });
        assertEquals("Endereço não pode ser nulo ou vazio", exception.getMessage());
    }
}
