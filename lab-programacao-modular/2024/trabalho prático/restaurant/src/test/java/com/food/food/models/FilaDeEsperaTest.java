package com.food.food.models;

import com.food.food.services.FilaDeEsperaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilaDeEsperaTest {

    private FilaDeEspera filaDeEspera;
    private FilaDeEsperaService filaDeEsperaService;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        filaDeEspera = new FilaDeEspera();
        filaDeEsperaService = new FilaDeEsperaService();
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Teste Cliente");
        cliente.setTelefone("123456789");
    }

    @Test
    public void testAddClienteFila() {
        Requisicao requisicao = new Requisicao(4, cliente);

        filaDeEspera.addClienteFila(requisicao);

        assertEquals(1, filaDeEspera.getRequisicoes().size());
        assertEquals(cliente.getId(), filaDeEspera.getRequisicoes().get(0).getCliente().getId());
    }

    @Test
    public void testAddClienteFilaNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            filaDeEspera.addClienteFila(null);
        });
        assertEquals("Requisição não pode ser nula", exception.getMessage());
    }

    @Test
    public void testRemoveClienteFila() {
        Requisicao requisicao = new Requisicao(4, cliente);
        filaDeEspera.addClienteFila(requisicao);

        filaDeEspera.removeClienteFila(requisicao.getId());

        assertTrue(filaDeEspera.getRequisicoes().isEmpty());
    }

    @Test
    public void testRemoveClienteFilaInvalidId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            filaDeEspera.removeClienteFila(null);
        });
        assertEquals("ID da requisição não pode ser nulo", exception.getMessage());
    }

    @Test
    public void testGetNextCliente() {
        Requisicao requisicao1 = new Requisicao(3, cliente);
        filaDeEspera.addClienteFila(requisicao1);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Outro Cliente");
        cliente2.setTelefone("987654321");

        Requisicao requisicao2 = new Requisicao(5, cliente2);
        filaDeEspera.addClienteFila(requisicao2);

        Requisicao nextCliente = filaDeEspera.getNextCliente(4);

        assertNotNull(nextCliente);
        assertEquals(cliente.getId(), nextCliente.getCliente().getId());
    }

    @Test
    public void testServiceAddClienteFila() {
        Requisicao requisicao = new Requisicao(4, cliente);

        filaDeEsperaService.addClienteFila(filaDeEspera, requisicao);

        assertEquals(1, filaDeEspera.getRequisicoes().size());
        assertEquals(cliente.getId(), filaDeEspera.getRequisicoes().get(0).getCliente().getId());
    }

    @Test
    public void testServiceRemoveClienteFila() {
        Requisicao requisicao = new Requisicao(4, cliente);
        filaDeEsperaService.addClienteFila(filaDeEspera, requisicao);

        filaDeEsperaService.removeClienteFila(filaDeEspera, requisicao.getId());

        assertTrue(filaDeEspera.getRequisicoes().isEmpty());
    }
}
