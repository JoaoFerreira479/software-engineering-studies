package com.food.food;

import com.food.food.models.Cliente;
import com.food.food.repositories.ClienteRepository;
import com.food.food.services.ClienteService;

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

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1L, "Mario Junior", "13245689");
    }

    @Test
    public void testBuscaClientePelaid() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.buscaClientePelaid(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals("Mario Junior", found.getNome());
    }

    @Test
    public void testSalvaCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente saved = clienteService.salvaCliente(cliente);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("Mario Junior", saved.getNome());
    }

    @Test
    public void testAtualizaDadosCliente() {
        Cliente updatedCliente = new Cliente(1L, "Maria Fernanda", "56821563");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(updatedCliente);

        Cliente updated = clienteService.atualizaDadosCliente(1L, updatedCliente);

        assertNotNull(updated);
        assertEquals(1L, updated.getId());
        assertEquals("Maria Fernanda", updated.getNome());
        assertEquals("56821563", updated.getTelefone());
    }

    @Test
    public void testDeletaCliente() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        Cliente deleted = clienteService.deletaCliente(1L);

        assertNotNull(deleted);
        assertEquals(1L, deleted.getId());
        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    public void testGetAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> allClientes = clienteService.getAllClientes();

        assertNotNull(allClientes);
        assertEquals(1, allClientes.size());
        assertEquals("Mario Junior", allClientes.get(0).getNome());
    }
}
