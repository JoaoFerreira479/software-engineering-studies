package com.food.food;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.food.food.models.Cliente;
import com.food.food.models.FilaDeEspera;
import com.food.food.models.Mesa;
import com.food.food.models.Requisicao;
import com.food.food.repositories.MesaRepository;
import com.food.food.repositories.RequisicaoRepository;
import com.food.food.services.ClienteService;
import com.food.food.services.RequisicaoService;

public class RequisicaoTest {

    @Mock
    private MesaRepository mesaRepository;

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private FilaDeEspera filaDeEspera;

    @InjectMocks
    private RequisicaoService requisicaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcessarRequisicaoComMesaDisponivel() {
        Requisicao requisicao = new Requisicao();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        requisicao.setCliente(cliente);
        requisicao.setNumeroDePessoas(4);

        List<Mesa> mesas = new ArrayList<>();
        Mesa mesa = new Mesa();
        mesa.setCapacidade(4);
        mesa.setOcupada(false);
        mesas.add(mesa);

        when(clienteService.buscaClientePelaid(cliente.getId())).thenReturn(cliente);
        when(mesaRepository.findAll()).thenReturn(mesas);

        requisicaoService.processarRequisicao(requisicao, filaDeEspera);

        assertTrue(mesa.isOcupada());
        assertEquals(cliente, mesa.getCliente());
        verify(mesaRepository, times(1)).save(mesa);
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testProcessarRequisicaoSemMesaDisponivel() {
        Requisicao requisicao = new Requisicao();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        requisicao.setCliente(cliente);
        requisicao.setNumeroDePessoas(4);

        List<Mesa> mesas = new ArrayList<>();

        when(clienteService.buscaClientePelaid(cliente.getId())).thenReturn(cliente);
        when(mesaRepository.findAll()).thenReturn(mesas);

        requisicaoService.processarRequisicao(requisicao, filaDeEspera);

        verify(filaDeEspera, times(1)).addClienteFila(requisicao);
        verify(mesaRepository, never()).save(any(Mesa.class));
        verify(requisicaoRepository, times(1)).save(requisicao);
    }

    @Test
    public void testHandleMesaLiberadaComClienteNaFila() {
        Mesa mesa = new Mesa();
        mesa.setCapacidade(4);
        mesa.setOcupada(true);
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        Requisicao requisicao = new Requisicao();
        requisicao.setCliente(cliente);
        requisicao.setNumeroDePessoas(4);

        when(filaDeEspera.getNextCliente(mesa.getCapacidade())).thenReturn(requisicao);

        requisicaoService.handleMesaLiberada(mesa, filaDeEspera);

        assertTrue(mesa.isOcupada());
        assertEquals(cliente, mesa.getCliente());
        verify(mesaRepository, times(1)).save(mesa);
        verify(filaDeEspera, times(1)).removeClienteFila(requisicao.getId());
    }

    @Test
    public void testHandleMesaLiberadaSemClienteNaFila() {
        Mesa mesa = new Mesa();
        mesa.setCapacidade(4);
        mesa.setOcupada(true);

        when(filaDeEspera.getNextCliente(mesa.getCapacidade())).thenReturn(null);

        requisicaoService.handleMesaLiberada(mesa, filaDeEspera);

        assertFalse(mesa.isOcupada());
        assertNull(mesa.getCliente());
        verify(mesaRepository, times(1)).save(mesa);
    }

    @Test
    public void testGetAllRequisicoes() {
        List<Requisicao> requisicoes = new ArrayList<>();
        when(requisicaoRepository.findAll()).thenReturn(requisicoes);

        List<Requisicao> result = requisicaoService.getAllRequisicoes();

        assertEquals(requisicoes, result);
        verify(requisicaoRepository, times(1)).findAll();
    }
}
