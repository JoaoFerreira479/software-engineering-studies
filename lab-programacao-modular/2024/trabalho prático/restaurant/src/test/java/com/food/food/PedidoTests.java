package com.food.food;

import com.food.food.DTO.PedidoRequest;
import com.food.food.models.ItemCardapio;
import com.food.food.models.Pedido;
import com.food.food.models.Requisicao;
import com.food.food.repositories.ItemCardapioRepository;
import com.food.food.repositories.PedidoRepository;
import com.food.food.repositories.RequisicaoRepository;
import com.food.food.services.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PedidoTests {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @Mock
    private RequisicaoRepository requisicaoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePedido() {
        Requisicao requisicao = new Requisicao();
        requisicao.setId(1L);
        ItemCardapio item = new ItemCardapio(1L, "Item 1", 10.0, "comida", null);
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setRequisicaoId(1L);
        pedidoRequest.setItensIds(Arrays.asList(1L));

        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(item));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido pedido = pedidoService.createPedido(pedidoRequest);

        assertNotNull(pedido);
        assertEquals(1, pedido.getItens().size());
        assertEquals(10.0, pedido.getValorTotalItens());
        verify(requisicaoRepository, times(1)).findById(1L);
        verify(itemCardapioRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    public void testCreatePedidoRequisicaoNaoEncontrada() {
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setRequisicaoId(1L);
        pedidoRequest.setItensIds(Arrays.asList(1L));

        when(requisicaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.createPedido(pedidoRequest);
        });
        assertEquals("Requisicao não encontrada", exception.getMessage());

        verify(requisicaoRepository, times(1)).findById(anyLong());
        verify(itemCardapioRepository, never()).findById(anyLong());
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }

    @Test
    public void testGetPedidoById() {
        Pedido pedido = new Pedido();
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido found = pedidoService.getPedidoById(1L);
        assertEquals(pedido, found);

        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPedidoByIdNotFound() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.getPedidoById(1L);
        });
        assertEquals("Pedido nao encontrado", exception.getMessage());

        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllPedidos() {
        List<Pedido> pedidos = Arrays.asList(new Pedido(), new Pedido());
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoService.getAllPedidos();
        assertEquals(2, result.size());

        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    public void testUpdatePedido() {
        Pedido existingPedido = new Pedido();
        Requisicao requisicao = new Requisicao();
        requisicao.setId(1L);
        ItemCardapio item = new ItemCardapio(1L, "Item 1", 10.0, "comida", null);
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setRequisicaoId(1L);
        pedidoRequest.setItensIds(Arrays.asList(1L));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(existingPedido));
        when(requisicaoRepository.findById(1L)).thenReturn(Optional.of(requisicao));
        when(itemCardapioRepository.findById(1L)).thenReturn(Optional.of(item));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pedido updatedPedido = pedidoService.updatePedido(1L, pedidoRequest);

        assertEquals(1, updatedPedido.getItens().size());
        assertEquals(10.0, updatedPedido.getValorTotalItens());
        verify(pedidoRepository, times(1)).findById(1L);
        verify(requisicaoRepository, times(1)).findById(1L);
        verify(itemCardapioRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    public void testUpdatePedidoNotFound() {
        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setRequisicaoId(1L);
        pedidoRequest.setItensIds(Arrays.asList(1L));

        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.updatePedido(1L, pedidoRequest);
        });
        assertEquals("Pedido não encontrado", exception.getMessage());

        verify(pedidoRepository, times(1)).findById(1L);
        verify(requisicaoRepository, never()).findById(anyLong());
        verify(itemCardapioRepository, never()).findById(anyLong());
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }
}
